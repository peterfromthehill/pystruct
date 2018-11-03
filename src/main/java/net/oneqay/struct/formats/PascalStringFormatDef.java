package net.oneqay.struct.formats;

import java.util.List;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.StructError;

public class PascalStringFormatDef extends StringFormatDef {
	@Override
	protected int doPack(final ByteStream buf, final int count, final int pos, final Object[] args) {
		final Object value = args[pos];

		if (!(value instanceof String))
			throw new StructError("argument for 'p' must be a string");

		buf.writeByte(Math.min(0xFF, Math.min(value.toString().length(), count - 1)));
		return super.doPack(buf, count - 1, pos, args);
	}

	@Override
	protected void doUnpack(final ByteStream buf, final int count, final List<Object> list) {
		int n = buf.readByte();
		if (n >= count)
			n = count - 1;
		super.doUnpack(buf, n, list);
		buf.skip(Math.max(count - n - 1, 0));
	}
}