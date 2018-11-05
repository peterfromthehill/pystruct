package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class LEIntFormatDef extends FormatDef {
    @Override
    protected void pack(final ByteStream buf, final Object value) {
        LEwriteInt(buf, get_int(value));
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        final int v = LEreadInt(buf);
        return Integer.valueOf(v);
    }
}
