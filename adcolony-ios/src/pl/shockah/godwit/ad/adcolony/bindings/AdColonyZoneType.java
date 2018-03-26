package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.rt.bro.ValuedEnum;

public enum AdColonyZoneType implements ValuedEnum {
	Interstitial,
	Native;

	@Override
	public long value() {
		return ordinal();
	}
}