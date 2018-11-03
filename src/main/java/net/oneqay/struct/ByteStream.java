package net.oneqay.struct;

public class ByteStream {
	char[] data;
	int len;
	int pos;

	ByteStream() {
		data = new char[10];
		len = 0;
		pos = 0;
	}

	ByteStream(final String s) {
		this(s, 0);
	}

	ByteStream(final String s, final int offset) {
		final int size = s.length() - offset;
		data = new char[size];
		s.getChars(offset, s.length(), data, 0);
		len = size;
		pos = 0;

//        System.out.println("s.length()=" + s.length() + ",offset=" + offset + ",size=" + size + ",data=" + Arrays.toString(data));
	}

	public int readByte() {
		return data[pos++] & 0xFF;
	}

	void read(final char[] buf, final int pos, final int len) {
		System.arraycopy(data, this.pos, buf, pos, len);
		this.pos += len;
	}

	public String readString(final int l) {
		final char[] data = new char[l];
		read(data, 0, l);
		return new String(data);
	}

	private void ensureCapacity(final int l) {
		if (pos + l >= data.length) {
			final char[] b = new char[(pos + l) * 2];
			System.arraycopy(data, 0, b, 0, pos);
			data = b;
		}
	}

	public void writeByte(final int b) {
		ensureCapacity(1);
		data[pos++] = (char) (b & 0xFF);
	}

	void write(final char[] buf, final int pos, final int len) {
		ensureCapacity(len);
		System.arraycopy(buf, pos, data, this.pos, len);
		this.pos += len;
	}

	public void writeString(final String s, final int pos, final int len) {
		final char[] data = new char[len];
		s.getChars(pos, len, data, 0);
		write(data, 0, len);
	}

	public int skip(final int l) {
		pos += l;
		return pos;
	}

	int size() {
		return pos;
	}

	@Override
	public String toString() {
		return new String(data, 0, pos);
	}
}
