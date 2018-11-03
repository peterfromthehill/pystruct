package net.oneqay.struct.formats;

import java.util.List;

import net.oneqay.struct.ByteStream;
import net.oneqay.struct.FormatDef;

public class PadFormatDef extends FormatDef {
	@Override
	protected int doPack(final ByteStream buf, int count, final int pos, final Object[] args) {
		while (count-- > 0)
			buf.writeByte(0);
		return 0;
	}

	@Override
	protected void doUnpack(final ByteStream buf, int count, final List<Object> list) {
		while (count-- > 0)
			buf.readByte();
	}
}