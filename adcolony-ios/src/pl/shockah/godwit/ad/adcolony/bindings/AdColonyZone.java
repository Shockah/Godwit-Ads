package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.block.VoidBlock3;
import org.robovm.rt.bro.annotation.MachineSizedUInt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@NativeClass
public class AdColonyZone extends NSObject {
	@Method(selector = "identifier")
	@Nonnull public native NSString getIdentifier();

	@Method(selector = "type")
	@Nonnull public native AdColonyZoneType getType();

	@Method(selector = "enabled")
	public native boolean isEnabled();

	@Method(selector = "rewarded")
	public native boolean isRewarded();

	@Method(selector = "viewsPerReward")
	@MachineSizedUInt public native long getViewsPerReward();

	@Method(selector = "viewsUntilReward")
	@MachineSizedUInt public native long getViewsUntilReward();

	@Method(selector = "rewardAmount")
	@MachineSizedUInt public native long getRewardAmount();

	@Method(selector = "rewardName")
	@Nonnull public native NSString getRewardName();

	@Method(selector = "setReward:")
	public native void setReward(@Nullable VoidBlock3<Boolean, NSString, Integer> reward);
}