package pl.shockah.godwit.ad.adcolony;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIViewController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
	@Nonnull private final Lock adLock = new ReentrantLock();

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
			adLock.lock();
			awaitingAd = null;
			AdColony.requestInterstitial(zone.getIdentifier(), null, ad -> {
				awaitingAd = ad;
				ad.setExpire(this::requestInterstitial);
				ad.setClose(this::requestInterstitial);
				adLock.unlock();
			}, error -> {
				System.out.println(error.description());
				adLock.unlock();
			});
		}
	}

	private void showAd(@Nullable AdProvider.RewardCallback rewardCallback) {
		synchronized (adLock) {
			adLock.lock();

			if (awaitingAd == null) {
				adLock.unlock();
				requestInterstitial();
				if (awaitingAd == null) {
					System.out.println("No ads to display.");
					return;
				}
				adLock.lock();
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

			adLock.unlock();
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