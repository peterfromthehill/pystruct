package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class ByteFormatDef extends FormatDef {
	@Override
	protected void pack(final ByteStream buf, final Object value) {
		buf.writeByte(get_int(value));
	}

	@Override
	protected Object unpack(final ByteStream buf) {
		int b = buf.readByte();
		if (b > Byte.MAX_VALUE)
			b -= 0x100;
		return Integer.valueOf(b);
	}
}