package pl.shockah.godwit.ad.adcolony;

import javax.annotation.Nonnull;

import pl.shockah.godwit.ad.AdProvider;

public class AdColonyProvider implements AdProvider {
	private AdColonyPlatformProvider platformProvider;

	public void setup(@Nonnull AdColonyPlatformProvider platformProvider) {
		this.platformProvider = platformProvider;
	}

	public void configure(@Nonnull String appId, @Nonnull String zoneId) {
		configure(appId, new String[] { zoneId });
	}

	public void configure(@Nonnull String appId, @Nonnull String[] zoneIds) {
		if (platformProvider == null)
			throw new IllegalStateException();
		platformProvider.configure(appId, zoneIds);
	}

	@Override
	public void showAd() {
		if (platformProvider != null)
			platformProvider.showAd();
	}

	@Override
	public void showRewardAd(@Nonnull RewardCallback callback) {
		if (platformProvider != null)
			platformProvider.showRewardAd(callback);
	}
}