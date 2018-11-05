package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class LEFloatFormatDef extends FormatDef {
    @Override
    protected void pack(final ByteStream buf, final Object value) {
        final int bits = Float.floatToIntBits((float) get_float(value));
        LEwriteInt(buf, bits);
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        final int bits = LEreadInt(buf);
        final float v = Float.intBitsToFloat(bits);
        if ((Float.isInfinite(v) || Float.isNaN(v))) { // Float.float_format == Float.Format.UNKNOWN &&
            throw new RuntimeException("can't unpack IEEE 754 special value on non-IEEE platform");
        }
        return new Float(v);
    }
}
