package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class PointerFormatDef extends FormatDef {
    public FormatDef init(final char name) {
        final String dataModel = System.getProperty("sun.arch.data.model");
        if (dataModel == null) {
            throw new RuntimeException("Can't determine if JVM is 32- or 64-bit");
        }
        final int length = "64".equals(dataModel) ? 8 : 4;
        super.init(name, length, length);
        return this;
    }

    @Override
    protected void pack(final ByteStream buf, final Object value) {
        throw new RuntimeException("Pointer packing/unpacking not implemented in Jython");
    }

    @Override
    protected Object unpack(final ByteStream buf) {
        throw new RuntimeException("Pointer packing/unpacking not implemented in Jython");
    }
}
