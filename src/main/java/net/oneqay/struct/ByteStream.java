package net.oneqay.struct;

import java.util.Arrays;

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

    ByteStream(final char[] a) {
        this(a, 0);
    }

    ByteStream(final char[] a, final int offset) {
        final int size = a.length - offset;
        data = Arrays.copyOf(a, size);
        len = size;
        pos = 0;
    }

    public int readByte() {
        return data[pos++] & 0xFF;
    }

    void read(final char[] buf, final int _pos, final int _len) {
        System.arraycopy(data, this.pos, buf, _pos, _len);
        this.pos += _len;
    }

    public String readString(final int l) {
        final char[] _data = new char[l];
        read(_data, 0, l);
        return new String(_data);
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

    void write(final char[] buf, final int _pos, final int _len) {
        ensureCapacity(_len);
        System.arraycopy(buf, _pos, data, this.pos, _len);
        this.pos += _len;
    }

    public void writeString(final String s, final int _pos, final int _len) {
        final char[] _data = new char[_len];
        s.getChars(_pos, _len, _data, 0);
        write(_data, 0, _len);
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
