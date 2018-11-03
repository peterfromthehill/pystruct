package net.oneqay.struct;

import java.math.BigInteger;
import java.util.List;

public class FormatDef {
	char name;
	int size;
	int alignment;

	public FormatDef init(final char name, final int size, final int alignment) {
		this.name = name;
		this.size = size;
		this.alignment = alignment;
		return this;
	}

	protected void pack(final ByteStream buf, final Object value) {
	}

	protected Object unpack(final ByteStream buf) {
		return null;
	}

	protected int doPack(final ByteStream buf, int count, int pos, final Object[] args) {
		if (pos + count > args.length)
			throw new StructError("insufficient arguments to pack");

		final int cnt = count;
		while (count-- > 0)
			pack(buf, args[pos++]);
		return cnt;
	}

	protected void doUnpack(final ByteStream buf, int count, final List<Object> list) {
		while (count-- > 0)
			list.add(unpack(buf));
	}

	protected int get_int(final Object value) {
		try {
			return ((Integer) value).intValue();
		} catch (final Exception ex) {
			throw new StructError("required argument is not an integer");
		}
	}

	protected long get_long(final Object value) {
		if (value instanceof Long) {
			return ((Long) value).longValue();
		} else
			return get_int(value);
	}

	protected BigInteger get_ulong(final Object value) {
		if (value instanceof Long) {
			final BigInteger v = (BigInteger) value;
			if (v.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
				throw new StructError("unsigned long int too long to convert");
			}
			return v;
		} else
			return BigInteger.valueOf(get_int(value));
	}

	protected double get_float(final Object value) {
		return ((Double) value).doubleValue();
	}

	protected void BEwriteInt(final ByteStream buf, final int v) {
		buf.writeByte((v >>> 24) & 0xFF);
		buf.writeByte((v >>> 16) & 0xFF);
		buf.writeByte((v >>> 8) & 0xFF);
		buf.writeByte((v >>> 0) & 0xFF);
	}

	protected void LEwriteInt(final ByteStream buf, final int v) {
		buf.writeByte((v >>> 0) & 0xFF);
		buf.writeByte((v >>> 8) & 0xFF);
		buf.writeByte((v >>> 16) & 0xFF);
		buf.writeByte((v >>> 24) & 0xFF);
	}

	protected int BEreadInt(final ByteStream buf) {
		final int b1 = buf.readByte();
		final int b2 = buf.readByte();
		final int b3 = buf.readByte();
		final int b4 = buf.readByte();
		return ((b1 << 24) + (b2 << 16) + (b3 << 8) + (b4 << 0));
	}

	protected int LEreadInt(final ByteStream buf) {
		final int b1 = buf.readByte();
		final int b2 = buf.readByte();
		final int b3 = buf.readByte();
		final int b4 = buf.readByte();
		return ((b1 << 0) + (b2 << 8) + (b3 << 16) + (b4 << 24));
	}
}
