package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;

public class BEUnsignedShortFormatDef extends BEShortFormatDef {
    @Override
    protected Object unpack(final ByteStream buf) {
        final int v = (buf.readByte() << 8) | buf.readByte();
        return new Integer(v);
    }
}
