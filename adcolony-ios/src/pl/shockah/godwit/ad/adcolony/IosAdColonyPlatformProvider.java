package pl.shockah.godwit.ad.adcolony;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIViewController;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import pl.shockah.godwit.ad.AdProvider;
import pl.shockah.godwit.ad.adcolony.bindings.AdColony;
import pl.shockah.godwit.ad.adcolony.bindings.AdColonyInterstitial;
import pl.shockah.godwit.ad.adcolony.bindings.AdColonyZone;

public class IosAdColonyPlatformProvider extends AdColonyPlatformProvider {
	@Nonnull public final UIViewController controller;

	private AdColonyZone zone = null;
	@Nullable private AdColonyInterstitial awaitingAd = null;
	private boolean retrieving = false;
	@Nonnull private final Object adLock = new Object();

	public IosAdColonyPlatformProvider(@Nonnull UIViewController controller) {
		this.controller = controller;
	}

	@Override
	public void configure(@Nonnull String appId, @Nonnull String[] zoneIds) {
		NSArray<NSString> nsArrayZoneIds = new NSMutableArray<>();
		for (String zoneId : zoneIds) {
			nsArrayZoneIds.add(new NSString(zoneId));
		}
		AdColony.configure(new NSString(appId), nsArrayZoneIds, null, zones -> {
			zone = zones.first();
			requestInterstitial();
		});
	}

	private void requestInterstitial() {
		synchronized (adLock) {
			if (retrieving)
				return;

			awaitingAd = null;
			retrieving = true;
			AdColony.requestInterstitial(zone.getIdentifier(), null, ad -> {
				awaitingAd = ad;
				ad.setExpire(this::requestInterstitial);
				ad.setClose(this::requestInterstitial);
				retrieving = false;
			}, error -> {
				System.out.println(error.description());
				retrieving = false;
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
				zone.setReward((success, name, amount) -> {
					rewardCallback.rewardCallback(success);
				});
			} else {
				zone.setReward(null);
			}

			awaitingAd = null;
			ad.show(controller);
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