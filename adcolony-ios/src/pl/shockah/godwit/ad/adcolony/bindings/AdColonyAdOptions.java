package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;

@NativeClass
public class AdColonyAdOptions extends NSObject {
	@Method(selector = "showPrePopup")
	public native boolean shouldShowPrePopup();

	@Method(selector = "setShowPrePopup:")
	public native void setShowPrePopup(boolean v);

	@Method(selector = "showPostPopup")
	public native boolean shouldShowPostPopup();

	@Method(selector = "setShowPostPopup:")
	public native void setShowPostPopup(boolean v);
}