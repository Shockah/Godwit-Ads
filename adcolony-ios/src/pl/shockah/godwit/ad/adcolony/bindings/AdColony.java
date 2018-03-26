package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.block.VoidBlock1;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@NativeClass
public class AdColony extends NSObject {
	@Method(selector = "configureWithAppID:zoneIDs:options:completion:")
	public static native void configure(@Nonnull NSString appID, @Nonnull NSArray<NSString> zoneIDs, @Nullable AdColonyAppOptions options, @Nullable VoidBlock1<NSArray<AdColonyZone>> completion);

	@Method(selector = "requestInterstitialInZone:options:success:failure:")
	public static native void requestInterstitial(@Nonnull NSString zoneID, @Nullable AdColonyAdOptions options, @Nonnull VoidBlock1<AdColonyInterstitial> success, @Nullable VoidBlock1<AdColonyAdRequestError> failure);

	//TODO: requestNativeAdViewInZone:size:options:viewController:success:failure:

	@Method(selector = "zoneForID:")
	@Nullable public static native AdColonyZone getZone(@Nonnull NSString zoneID);

	@Method(selector = "getAdvertisingID")
	@Nonnull public static native NSString getAdvertisingID();

	@Method(selector = "getUserID")
	@Nonnull public static native NSString getUserID();

	@Method(selector = "setAppOptions:")
	public static native void setAppOptions(@Nonnull AdColonyAppOptions options);

	@Method(selector = "getAppOptions")
	@Nonnull public static native AdColonyAppOptions getAppOptions();

	//TODO: sendCustomMessageOfType:withContent:reply:

	//TODO: iapCompleteWithTransactionID:productID:price:currencyCode:

	@Method(selector = "getSDKVersion")
	@Nonnull public static native NSString getSDKVersion();
}