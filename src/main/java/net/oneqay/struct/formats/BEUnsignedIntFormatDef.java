package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class BEUnsignedIntFormatDef extends FormatDef {
    @Override
    protected void pack(final ByteStream buf, final Object value) {
        BEwriteInt(buf, (int) (get_long(value) & 0xFFFFFFFF));
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        long v = BEreadInt(buf);
        if (v < 0) {
            v += 0x100000000L;
        }
        return new Long(v);
    }
}
