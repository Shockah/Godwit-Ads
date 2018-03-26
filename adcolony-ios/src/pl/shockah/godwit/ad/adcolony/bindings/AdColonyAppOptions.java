package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@NativeClass
public class AdColonyAppOptions extends NSObject {
	@Method(selector = "disableLogging")
	public native boolean isLoggingDisabled();

	@Method(selector = "setDisableLogging:")
	public native void setLoggingDisabled(boolean v);

	@Method(selector = "userID")
	@Nullable public native NSString getUserID();

	@Method(selector = "setUserID:")
	public native void setUserID(@Nullable NSString userID);

	@Method(selector = "adOrientation")
	@Nonnull public native AdColonyOrientation getAdOrientation();

	@Method(selector = "setAdOrientation:")
	public native void setAdOrientation(@Nonnull AdColonyOrientation adOrientation);

	@Method(selector = "testMode")
	public native boolean isTestMode();

	@Method(selector = "setTestMode:")
	public native void setTestMode(boolean v);

	@Method(selector = "mediationNetwork")
	@Nullable public native NSString getMediationNetwork();

	@Method(selector = "setMediationNetwork:")
	public native void setMediationNetwork(@Nullable NSString mediationNetwork);

	@Method(selector = "mediationNetworkVersion")
	@Nullable public native NSString getMediationNetworkVersion();

	@Method(selector = "setMediationNetworkVersion:")
	public native void setMediationNetworkVersion(@Nullable NSString mediationNetworkVersion);

	@Method(selector = "plugin")
	@Nullable public native NSString getPlugin();

	@Method(selector = "setPlugin:")
	public native void setPlugin(@Nullable NSString plugin);

	@Method(selector = "pluginVersion")
	@Nullable public native NSString getPluginVersion();

	@Method(selector = "setPluginVersion:")
	public native void setPluginVersion(@Nullable NSString pluginVersion);
}