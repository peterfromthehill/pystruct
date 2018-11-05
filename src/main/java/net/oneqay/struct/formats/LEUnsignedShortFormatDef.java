package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;

public class LEUnsignedShortFormatDef extends LEShortFormatDef {
    @Override
    protected Object unpack(final ByteStream buf) {
        final int v = buf.readByte() | (buf.readByte() << 8);
        return Integer.valueOf(v);
    }
}
