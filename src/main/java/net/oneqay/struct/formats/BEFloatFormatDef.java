package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class BEFloatFormatDef extends FormatDef {
	@Override
	protected void pack(final ByteStream buf, final Object value) {
		final int bits = Float.floatToIntBits((float) get_float(value));
		BEwriteInt(buf, bits);
	}

	@Override
	protected Object unpack(final ByteStream buf) {
		final int bits = BEreadInt(buf);
		final float v = Float.intBitsToFloat(bits);
		if ((Float.isInfinite(v) || Float.isNaN(v))) { // PyFloat.float_format == PyFloat.Format.UNKNOWN &&
			throw new RuntimeException("can't unpack IEEE 754 special value on non-IEEE platform");
		}
		return new Float(v);
	}
}
