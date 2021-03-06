package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class LEDoubleFormatDef extends FormatDef {
    @Override
    protected void pack(final ByteStream buf, final Object value) {
        final long bits = Double.doubleToLongBits(get_float(value));
        LEwriteInt(buf, (int) (bits & 0xFFFFFFFF));
        LEwriteInt(buf, (int) (bits >>> 32));
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        final long bits = (LEreadInt(buf) & 0xFFFFFFFFL) + (((long) LEreadInt(buf)) << 32);
        final double v = Double.longBitsToDouble(bits);
        if ((Double.isInfinite(v) || Double.isNaN(v))) { // PyFloat.double_format == PyFloat.Format.UNKNOWN &&
            throw new RuntimeException("can't unpack IEEE 754 special value on non-IEEE platform");
        }
        return Float.valueOf((float) v);
    }
}
