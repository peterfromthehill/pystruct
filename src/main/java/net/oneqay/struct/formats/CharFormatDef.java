package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;
import net.oneqay.struct.StructError;

public class CharFormatDef extends FormatDef {
	@Override
	protected void pack(final ByteStream buf, final Object value) {
		if (!(value instanceof String) || ((String) value).length() != 1) {
			throw new StructError("char format require string of length 1");
		}
		buf.writeByte(value.toString().charAt(0));
	}

	@Override
	protected Object unpack(final ByteStream buf) {
		final char[] charBuf = { (char) buf.readByte() };
		return new String(charBuf);
	}
}
