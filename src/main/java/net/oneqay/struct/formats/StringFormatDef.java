package net.oneqay.struct.formats;

import java.util.List;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;
import net.oneqay.struct.StructError;

public class StringFormatDef extends FormatDef {
	@Override
	protected int doPack(final ByteStream buf, int count, final int pos, final Object[] args) {
		final Object value = args[pos];

		if (!(value instanceof String))
			throw new StructError("argument for 's' must be a string");

		final String s = value.toString();
		final int len = s.length();
		buf.writeString(s, 0, Math.min(count, len));
		if (len < count) {
			count -= len;
			for (int i = 0; i < count; i++)
				buf.writeByte(0);
		}
		return 1;
	}

	@Override
	protected void doUnpack(final ByteStream buf, final int count, final List<Object> list) {
		list.add(new String(buf.readString(count)));
	}
}
