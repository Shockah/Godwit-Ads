package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.apple.foundation.NSCoder;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSErrorUserInfo;
import org.robovm.objc.annotation.NativeClass;

@NativeClass
public class AdColonyAdRequestError extends NSError {
	protected AdColonyAdRequestError(Handle h, long handle) {
		super(h, handle);
	}

	protected AdColonyAdRequestError(SkipInit skipInit) {
		super(skipInit);
	}

	public AdColonyAdRequestError(String domain, long code, NSErrorUserInfo dict) {
		super(domain, code, dict);
	}

	public AdColonyAdRequestError(NSCoder decoder) {
		super(decoder);
	}
}