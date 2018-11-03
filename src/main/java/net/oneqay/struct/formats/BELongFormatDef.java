package net.oneqay.struct.formats;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class BELongFormatDef extends FormatDef {
	@Override
	protected void pack(final ByteStream buf, final Object value) {
		final long lvalue = get_long(value);
		final int high = (int) ((lvalue & 0xFFFFFFFF00000000L) >> 32);
		final int low = (int) (lvalue & 0x00000000FFFFFFFFL);
		BEwriteInt(buf, high);
		BEwriteInt(buf, low);
	}

	@Override
	protected Object unpack(final ByteStream buf) {
		final long high = ((long) (BEreadInt(buf)) << 32) & 0xFFFFFFFF00000000L;
		final long low = BEreadInt(buf) & 0x00000000FFFFFFFFL;
		final long result = (high | low);
		return new Long(result);
	}
}