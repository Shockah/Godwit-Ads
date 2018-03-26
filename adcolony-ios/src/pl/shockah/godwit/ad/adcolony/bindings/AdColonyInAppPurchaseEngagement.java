package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.rt.bro.ValuedEnum;

public enum AdColonyInAppPurchaseEngagement implements ValuedEnum {
	EndCard,
	Overlay;

	@Override
	public long value() {
		return ordinal();
	}
}