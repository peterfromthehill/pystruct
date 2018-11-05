package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class LEUnsignedIntFormatDef extends FormatDef {
    @Override
    protected void pack(final ByteStream buf, final Object value) {
        LEwriteInt(buf, (int) (get_long(value) & 0xFFFFFFFF));
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        long v = LEreadInt(buf);
        if (v < 0) {
            v += 0x100000000L;
        }
        return Long.valueOf(v);
    }
}
