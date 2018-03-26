package pl.shockah.godwit.ad.adcolony.bindings;

import org.robovm.rt.bro.ValuedEnum;

public enum AdColonyOrientation implements ValuedEnum {
	Portrait,
	Landscape,
	All;

	@Override
	public long value() {
		return ordinal();
	}
}