package pl.shockah.godwit.ad;

import javax.annotation.Nonnull;

public interface AdProvider {
	void showAd();

	void showRewardAd(@Nonnull RewardCallback callback);

	interface RewardCallback {
		void rewardCallback(boolean watched);
	}
}