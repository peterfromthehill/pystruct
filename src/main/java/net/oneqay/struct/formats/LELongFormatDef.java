package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class LELongFormatDef extends FormatDef {
    @Override
    protected void pack(final ByteStream buf, final Object value) {
        final long lvalue = get_long(value);
        final int high = (int) ((lvalue & 0xFFFFFFFF00000000L) >> 32);
        final int low = (int) (lvalue & 0x00000000FFFFFFFFL);
        LEwriteInt(buf, low);
        LEwriteInt(buf, high);
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        final long low = LEreadInt(buf) & 0x00000000FFFFFFFFL;
        final long high = ((long) (LEreadInt(buf)) << 32) & 0xFFFFFFFF00000000L;
        final long result = (high | low);
        return Long.valueOf(result);
    }
}
