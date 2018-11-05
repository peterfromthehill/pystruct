package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class BEDoubleFormatDef extends FormatDef {
    @Override
    protected void pack(final ByteStream buf, final Object value) {
        final long bits = Double.doubleToLongBits(get_float(value));
        BEwriteInt(buf, (int) (bits >>> 32));
        BEwriteInt(buf, (int) (bits & 0xFFFFFFFF));
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        final long bits = (((long) BEreadInt(buf)) << 32) + (BEreadInt(buf) & 0xFFFFFFFFL);
        final double v = Double.longBitsToDouble(bits);
        if ((Double.isInfinite(v) || Double.isNaN(v))) { // PyFloat.double_format == PyFloat.Format.UNKNOWN &&
            throw new RuntimeException("can't unpack IEEE 754 special value on non-IEEE platform");
        }
        return new Float(v);
    }
}
