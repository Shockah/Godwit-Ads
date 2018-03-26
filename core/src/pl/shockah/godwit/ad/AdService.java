package pl.shockah.godwit.ad;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import pl.shockah.godwit.Godwit;
import pl.shockah.godwit.platform.PlatformService;

public abstract class AdService implements PlatformService, AdProvider {
	@Nonnull protected final List<AdProvider> providers = new ArrayList<>();

	public void register() {
		Godwit.getInstance().platformServiceProvider.register(AdService.class, this);
	}

	public final void register(@Nonnull AdProvider provider) {
		providers.add(provider);
	}

	@Override
	public void showAd() {
		if (providers.isEmpty())
			return;
		providers.get(0).showAd();
	}

	@Override
	public void showRewardAd(@Nonnull RewardCallback callback) {
		if (providers.isEmpty()) {
			callback.rewardCallback(false);
			return;
		}
		providers.get(0).showRewardAd(callback);
	}
}