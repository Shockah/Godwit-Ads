package pl.shockah.godwit.ad.adcolony;

import javax.annotation.Nonnull;

import pl.shockah.godwit.ad.AdProvider;

public abstract class AdColonyPlatformProvider {
	public final void configure(@Nonnull String appId, @Nonnull String zoneId) {
		configure(appId, new String[] { zoneId });
	}

	public abstract void configure(@Nonnull String appId, @Nonnull String[] zoneIds);

	public abstract void showAd();

	public abstract void showRewardAd(@Nonnull AdProvider.RewardCallback callback);
}