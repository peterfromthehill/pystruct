package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;

public class UnsignedByteFormatDef extends ByteFormatDef {
	@Override
	protected Object unpack(final ByteStream buf) {
		return Integer.valueOf(buf.readByte());
	}
}
