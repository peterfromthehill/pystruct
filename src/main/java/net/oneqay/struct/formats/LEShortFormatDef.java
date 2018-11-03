package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class LEShortFormatDef extends FormatDef {
	@Override
	protected void pack(final ByteStream buf, final Object value) {
		final int v = get_int(value);
		buf.writeByte(v & 0xFF);
		buf.writeByte((v >> 8) & 0xFF);
	}

	@Override
	protected Object unpack(final ByteStream buf) {
		int v = buf.readByte() | (buf.readByte() << 8);
		if (v > Short.MAX_VALUE)
			v -= 0x10000;
		return new Integer(v);
	}
}
