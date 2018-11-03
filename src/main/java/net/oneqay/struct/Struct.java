package net.oneqay.struct;

import java.util.ArrayList;
import java.util.List;

import net.oneqay.struct.formats.BEDoubleFormatDef;
import net.oneqay.struct.formats.BEFloatFormatDef;
import net.oneqay.struct.formats.BEIntFormatDef;
import net.oneqay.struct.formats.BELongFormatDef;
import net.oneqay.struct.formats.BEShortFormatDef;
import net.oneqay.struct.formats.BEUnsignedIntFormatDef;
import net.oneqay.struct.formats.BEUnsignedLongFormatDef;
import net.oneqay.struct.formats.BEUnsignedShortFormatDef;
import net.oneqay.struct.formats.ByteFormatDef;
import net.oneqay.struct.formats.CharFormatDef;
import net.oneqay.struct.formats.LEDoubleFormatDef;
import net.oneqay.struct.formats.LEFloatFormatDef;
import net.oneqay.struct.formats.LEIntFormatDef;
import net.oneqay.struct.formats.LELongFormatDef;
import net.oneqay.struct.formats.LEShortFormatDef;
import net.oneqay.struct.formats.LEUnsignedIntFormatDef;
import net.oneqay.struct.formats.LEUnsignedLongFormatDef;
import net.oneqay.struct.formats.LEUnsignedShortFormatDef;
import net.oneqay.struct.formats.PadFormatDef;
import net.oneqay.struct.formats.PascalStringFormatDef;
import net.oneqay.struct.formats.PointerFormatDef;
import net.oneqay.struct.formats.StringFormatDef;
import net.oneqay.struct.formats.UnsignedByteFormatDef;

public class Struct {
	private static FormatDef getentry(final char c, final FormatDef[] f) {
		for (int i = 0; i < f.length; i++) {
			if (f[i].name == c)
				return f[i];
		}
		throw new StructError("bad char in struct format");
	}

	private static int align(int size, final FormatDef e) {
		if (e.alignment != 0) {
			size = ((size + e.alignment - 1) / e.alignment) * e.alignment;
		}
		return size;
	}

	static public int calcsize(final String format) {
		final FormatDef[] f = whichtable(format);
		return calcsize(format, f);
	}

	static int calcsize(final String format, final FormatDef[] f) {
		int size = 0;

		final int len = format.length();
		for (int j = 0; j < len; j++) {
			char c = format.charAt(j);
			if (j == 0 && (c == '@' || c == '<' || c == '>' || c == '=' || c == '!'))
				continue;
			if (Character.isWhitespace(c))
				continue;
			int num = 1;
			if (Character.isDigit(c)) {
				num = Character.digit(c, 10);
				while (++j < len && Character.isDigit((c = format.charAt(j)))) {
					final int x = num * 10 + Character.digit(c, 10);
					if (x / 10 != num)
						throw new StructError("overflow in item count");
					num = x;
				}
				if (j >= len)
					break;
			}

			final FormatDef e = getentry(c, f);

			final int itemsize = e.size;
			size = align(size, e);
			final int x = num * itemsize;
			size += x;
			if (x / itemsize != num || size < 0)
				throw new StructError("total struct size too long");
		}
		return size;
	}

	private static FormatDef[] lilendian_table = { //
			new PadFormatDef().init('x', 1, 0), //
			new ByteFormatDef().init('b', 1, 0), //
			new UnsignedByteFormatDef().init('B', 1, 0), //
			new LEUnsignedIntFormatDef().init('L', 4, 0), //
			new LEUnsignedIntFormatDef().init('I', 4, 0), //
			new StringFormatDef().init('s', 1, 0), //
			new CharFormatDef().init('c', 1, 0), //
			new PascalStringFormatDef().init('p', 1, 0), //
			new LEShortFormatDef().init('h', 2, 0), //
			new LEUnsignedShortFormatDef().init('H', 2, 0), //
			new LEIntFormatDef().init('i', 4, 0), //
			new LEIntFormatDef().init('l', 4, 0), //
			new LELongFormatDef().init('q', 8, 0), //
			new LEUnsignedLongFormatDef().init('Q', 8, 0), //
			new LEFloatFormatDef().init('f', 4, 0), //
			new LEDoubleFormatDef().init('d', 8, 0), //
	};

	private static FormatDef[] bigendian_table = { //
			new PadFormatDef().init('x', 1, 0), //
			new ByteFormatDef().init('b', 1, 0), //
			new UnsignedByteFormatDef().init('B', 1, 0), //
			new BEUnsignedIntFormatDef().init('I', 4, 0), //
			new BEUnsignedIntFormatDef().init('L', 4, 0), //
			new StringFormatDef().init('s', 1, 0), //
			new CharFormatDef().init('c', 1, 0), //
			new PascalStringFormatDef().init('p', 1, 0), //
			new BEShortFormatDef().init('h', 2, 0), //
			new BEUnsignedShortFormatDef().init('H', 2, 0), //
			new BEIntFormatDef().init('i', 4, 0), //
			new BEIntFormatDef().init('l', 4, 0), //
			new BELongFormatDef().init('q', 8, 0), //
			new BEUnsignedLongFormatDef().init('Q', 8, 0), //
			new BEFloatFormatDef().init('f', 4, 0), //
			new BEDoubleFormatDef().init('d', 8, 0), //
	};

	private static FormatDef[] native_table = { //
			new PadFormatDef().init('x', 1, 0), //
			new ByteFormatDef().init('b', 1, 0), //
			new UnsignedByteFormatDef().init('B', 1, 0), //
			new BEUnsignedIntFormatDef().init('I', 4, 4), //
			new BEUnsignedIntFormatDef().init('L', 4, 4), //
			new StringFormatDef().init('s', 1, 0), //
			new CharFormatDef().init('c', 1, 0), //
			new PascalStringFormatDef().init('p', 1, 0), //
			new BEShortFormatDef().init('h', 2, 2), //
			new BEUnsignedShortFormatDef().init('H', 2, 2), //
			new BEIntFormatDef().init('i', 4, 4), //
			new BEIntFormatDef().init('l', 4, 4), //
			new BELongFormatDef().init('q', 8, 8), //
			new BEUnsignedLongFormatDef().init('Q', 8, 8), //
			new BEFloatFormatDef().init('f', 4, 4), //
			new BEDoubleFormatDef().init('d', 8, 8), //
			new PointerFormatDef().init('P') //
	};

	static FormatDef[] whichtable(final String pfmt) {
		final char c = pfmt.charAt(0);
		switch (c) {
		case '<':
			return lilendian_table;
		case '>':
		case '!':
			// Network byte order is big-endian
			return bigendian_table;
		case '=':
			return bigendian_table;
		case '@':
		default:
			return native_table;
		}
	}

	public static Object[] unpack(final String format, final String string) {
		final FormatDef[] f = whichtable(format);
		final int size = calcsize(format, f);
		final int len = string.length();
		if (size != len)
			throw new StructError("unpack str size does not match format");
		return unpack(f, size, format, new ByteStream(string));
	}

// TODO: PyArray	
//	public static Object[] unpack(final String format, final PyArray buffer) {
//		final String string = buffer.tostring();
//		final FormatDef[] f = whichtable(format);
//		final int size = calcsize(format, f);
//		final int len = string.length();
//		if (size != len)
//			throw StructError("unpack str size does not match format");
//		return unpack(f, size, format, new ByteStream(string));
//	}

	public static Object[] unpack_from(final String format, final String string) {
		return unpack_from(format, string, 0);
	}

	public static Object[] unpack_from(final String format, final String string, final int offset) {
		final FormatDef[] f = whichtable(format);
		final int size = calcsize(format, f);
		final int len = string.length();
		if (size >= (len - offset + 1))
			throw new StructError("unpack_from str size does not match format");
		return unpack(f, size, format, new ByteStream(string, offset));
	}

	static Object[] unpack(final FormatDef[] f, final int size, final String format, final ByteStream str) {
		final List<Object> res = new ArrayList<Object>();
		final int flen = format.length();
		for (int j = 0; j < flen; j++) {
			char c = format.charAt(j);
			if (j == 0 && (c == '@' || c == '<' || c == '>' || c == '=' || c == '!'))
				continue;
			if (Character.isWhitespace(c))
				continue;
			int num = 1;
			if (Character.isDigit(c)) {
				num = Character.digit(c, 10);
				while (++j < flen && Character.isDigit((c = format.charAt(j))))
					num = num * 10 + Character.digit(c, 10);
				if (j > flen)
					break;
			}

			final FormatDef e = getentry(c, f);

			str.skip(align(str.size(), e) - str.size());

			e.doUnpack(str, num, res);
		}
		return res.toArray();
	}

}
