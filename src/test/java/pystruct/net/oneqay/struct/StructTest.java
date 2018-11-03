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
		assertEquals(4, a.length);
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
	}

	@Test
	public void testLong() {
		final byte[] b = { 0, 0, 16, 0, 0, 0, 8, 0, 0, 0, 16, 0 };
		final Object[] o = Struct.unpack(">III", new String(b));
		assertEquals(3, o.length);

		final Object o1 = o[0];
		assertTrue(o1 instanceof Long);
		final Long l1 = (Long) o1;

		final Object o2 = o[1];
		assertTrue(o2 instanceof Long);
		final Long l2 = (Long) o2;

		final Object o3 = o[2];
		assertTrue(o3 instanceof Long);
		final Long l3 = (Long) o3;

		assertEquals(new Long(4096), o1);
		assertEquals(new Long(2048), o2);
		assertEquals(new Long(4096), o3);
		assertTrue(l1.equals(l3));
	}
}
