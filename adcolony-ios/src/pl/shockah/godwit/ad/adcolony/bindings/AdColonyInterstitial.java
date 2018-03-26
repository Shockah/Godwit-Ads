package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.block.VoidBlock2;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@NativeClass
public class AdColonyInterstitial extends NSObject {
	@Method(selector = "zoneID")
	@Nonnull public native NSString getZoneID();

	@Method(selector = "expired")
	public native boolean isExpired();

	@Method(selector = "audioEnabled")
	public native boolean isAudioEnabled();

	@Method(selector = "iapEnabled")
	public native boolean isInAppPurchaseEnabled();

	@Method(selector = "setOpen:")
	public native void setOpen(@Nullable Runnable open);

	@Method(selector = "setClose:")
	public native void setClose(@Nullable Runnable open);

	@Method(selector = "setAudioStart:")
	public native void setAudioStart(@Nullable Runnable audioStart);

	@Method(selector = "setAudioStop:")
	public native void setAudioStop(@Nullable Runnable audioStop);

	@Method(selector = "setExpire:")
	public native void setExpire(@Nullable Runnable expire);

	@Method(selector = "setLeftApplication:")
	public native void setLeftApplication(@Nullable Runnable leftApplication);

	@Method(selector = "setClick:")
	public native void setClick(@Nullable Runnable click);

	@Method(selector = "setIapOpportunity:")
	public native void setInAppPurchaseOpportunity(@Nullable VoidBlock2<NSString, AdColonyInAppPurchaseEngagement> inAppPurchaseOpportunity);

	@Method(selector = "showWithPresentingViewController:")
	public native boolean show(@Nonnull UIViewController presentingViewController);

	@Method(selector = "cancel")
	public native void cancel();
}