package net.oneqay.struct.formats;

import java.math.BigInteger;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;
import net.oneqay.struct.StructError;

public class BEUnsignedLongFormatDef extends FormatDef {
	@Override
	protected void pack(final ByteStream buf, final Object value) {
		final BigInteger bi = get_ulong(value);
		if (bi.compareTo(BigInteger.valueOf(0)) < 0) {
			throw new StructError("can't convert negative long to unsigned");
		}
		final long lvalue = bi.longValue(); // underflow is OK -- the bits are correct
		final int high = (int) ((lvalue & 0xFFFFFFFF00000000L) >> 32);
		final int low = (int) (lvalue & 0x00000000FFFFFFFFL);
		BEwriteInt(buf, high);
		BEwriteInt(buf, low);
	}

	@Override
	protected Object unpack(final ByteStream buf) {
		final long high = (BEreadInt(buf) & 0X00000000FFFFFFFFL);
		final long low = (BEreadInt(buf) & 0X00000000FFFFFFFFL);
		java.math.BigInteger result = java.math.BigInteger.valueOf(high);
		result = result.multiply(java.math.BigInteger.valueOf(0x100000000L));
		result = result.add(java.math.BigInteger.valueOf(low));
		return new Long(result.longValue());
	}
}
