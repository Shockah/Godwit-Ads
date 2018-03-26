package pl.shockah.godwit.ad.adcolony;

import android.support.v4.app.FragmentActivity;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyZone;

import java.lang.ref.WeakReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import pl.shockah.godwit.ad.AdProvider;

public class AndroidAdColonyPlatformProvider extends AdColonyPlatformProvider {
	@Nonnull public final WeakReference<FragmentActivity> activityRef;

	private String firstZoneId;
	@Nullable private AdColonyInterstitial awaitingAd = null;
	private boolean retrieving = false;
	@Nonnull private final Object adLock = new Object();

	public AndroidAdColonyPlatformProvider(@Nonnull FragmentActivity activity) {
		activityRef = new WeakReference<>(activity);
	}

	private AdColonyZone getZone() {
		return AdColony.getZone(firstZoneId);
	}

	@Override
	public void configure(@Nonnull String appId, @Nonnull String[] zoneIds) {
		firstZoneId = zoneIds[0];
		AdColony.configure(activityRef.get(), appId, zoneIds);
		requestInterstitial();
	}

	private void requestInterstitial() {
		synchronized (adLock) {
			if (retrieving)
				return;

			awaitingAd = null;
			retrieving = true;
			AdColony.requestInterstitial(firstZoneId, new AdColonyInterstitialListener() {
				@Override
				public void onRequestFilled(AdColonyInterstitial ad) {
					awaitingAd = ad;
					retrieving = false;
					AndroidAdColonyPlatformProvider.this.notify();
				}

				@Override
				public void onRequestNotFilled(AdColonyZone zone) {
					System.out.println("Couldn't retrieve an interstitial.");
					retrieving = false;
					AndroidAdColonyPlatformProvider.this.notify();
				}

				@Override
				public void onExpiring(AdColonyInterstitial ad) {
					requestInterstitial();
				}

				@Override
				public void onClosed(AdColonyInterstitial ad) {
					requestInterstitial();
				}
			});
		}
	}

	private void showAd(@Nullable AdProvider.RewardCallback rewardCallback) {
		synchronized (adLock) {
			while (retrieving) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (awaitingAd == null) {
				requestInterstitial();
				if (awaitingAd == null) {
					System.out.println("No ads to display.");
					return;
				}
			}

			AdColonyInterstitial ad = awaitingAd;
			if (rewardCallback != null) {
				AdColony.setRewardListener(reward -> {
					rewardCallback.rewardCallback(reward.success());
				});
			} else {
				AdColony.setRewardListener(reward -> {});
			}

			awaitingAd = null;
			ad.show();
		}
	}

	@Override
	public void showAd() {
		showAd(null);
	}

	@Override
	public void showRewardAd(@Nonnull AdProvider.RewardCallback callback) {
		showAd(callback);
	}
}