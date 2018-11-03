package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class BEIntFormatDef extends FormatDef {
	@Override
	protected void pack(final ByteStream buf, final Object value) {
		BEwriteInt(buf, get_int(value));
	}

	@Override
	protected Object unpack(final ByteStream buf) {
		return new Integer(BEreadInt(buf));
	}
}