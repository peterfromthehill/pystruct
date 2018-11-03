# pystruct
Java implementation of Pythons Struct


# Usage
    byte[] magicbyte = {0, 0, 0, 1, 66, 117, 100, 49};
	final Object[] magic = Struct.unpack(">II", new String(magicbyte));
    // magic[0] = Long(1)
    // magic[1] = Long(1114989617)
