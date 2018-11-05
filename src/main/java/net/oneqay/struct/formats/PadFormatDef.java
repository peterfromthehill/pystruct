package net.oneqay.struct.formats;

import java.util.List;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class PadFormatDef extends FormatDef {
    @Override
    protected int doPack(final ByteStream buf, final int _count, final int pos, final Object[] args) {
        int count = _count;
        while (count-- > 0) {
            buf.writeByte(0);
        }
        return 0;
    }

    @Override
    protected void doUnpack(final ByteStream buf, final int _count, final List<Object> list) {
        int count = _count;
        while (count-- > 0) {
            buf.readByte();
        }
    }
}
