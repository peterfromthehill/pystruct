package pystruct.net.oneqay.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import net.oneqay.struct.Struct;

public class StructTest {
	@Test
	public void testChar() {
		final String t = "aaaa";
		final Object[] a = Struct.unpack(">4c", t);
		assertNotNull(a);
		assertEquals(a.length, 4);
		assertEquals("a", a[0]);
		assertEquals("a", a[1]);
		assertEquals("a", a[2]);
		assertEquals("a", a[3]);
	}

	@Test
	public void testString() {
		final String t = "aaaa";
		final Object[] a = Struct.unpack(">4s", t);
		assertEquals(1, a.length);
		final Object s = a[0];
		assertTrue(s instanceof String);
		assertTrue(t.equals(s));
		System.out.println(s);
	}

//	@Test
//	void testLong() {
//		final
//	}
}
