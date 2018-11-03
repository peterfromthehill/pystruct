package pystruct.net.oneqay.struct;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.oneqay.struct.Struct;

public class StructTest {
	class testCase {
		public String fmt;
		public Object expect;
		public String big;
		public String lil;

		testCase(final String fmt, final Object expect, final String big, final String lil) {
			this.fmt = fmt;
			this.expect = expect;
			this.big = big;
			this.lil = lil;
		}
	}

	@Test
	public void testLong() {
		final List<testCase> tests = new ArrayList<testCase>();

		tests.add(new testCase("c", "a", "a", "a")); //
		tests.add(new testCase("s", "a", "a", "a")); //
		tests.add(new testCase("0s", "", "helloworld", "helloworld")); //
		tests.add(new testCase("1s", "h", "helloworld", "helloworld")); //
		tests.add(new testCase("9s", "helloworl", "helloworld", "helloworld")); //
		tests.add(new testCase("10s", "helloworld", "helloworld", "helloworld")); //

		tests.add(new testCase("b", 7, "\7", "\7"));
		tests.add(new testCase("b", -7, "\371", "\371"));
		tests.add(new testCase("B", 7, "\7", "\7"));
		tests.add(new testCase("B", 249, "\371", "\371"));
		tests.add(new testCase("h", 700, "\002\274", "\274\002"));
		tests.add(new testCase("h", -700, "\375D", "D\375"));
		tests.add(new testCase("H", 700, "\002\274", "\274\002"));
		tests.add(new testCase("H", 0x10000 - 700, "\375D", "D\375"));
		tests.add(new testCase("i", 70000000, "\004,\035\200", "\200\035,\004"));
		tests.add(new testCase("i", -70000000, "\373\323\342\200", "\200\342\323\373"));
		tests.add(new testCase("I", 70000000L, "\004,\035\200", "\200\035,\004"));
		tests.add(new testCase("I", 0x100000000L - 70000000, "\373\323\342\200", "\200\342\323\373"));
		tests.add(new testCase("l", 70000000, "\004,\035\200", "\200\035,\004"));
		tests.add(new testCase("l", -70000000, "\373\323\342\200", "\200\342\323\373"));
		tests.add(new testCase("L", 70000000L, "\004,\035\200", "\200\035,\004"));
		tests.add(new testCase("L", 0x100000000L - 70000000, "\373\323\342\200", "\200\342\323\373"));
		tests.add(new testCase("f", 2.0, "@\000\000\000", "\000\000\000@"));
		tests.add(new testCase("d", 2.0, "@\000\000\000\000\000\000\000", "\000\000\000\000\000\000\000@"));
		tests.add(new testCase("f", -2.0, "\300\000\000\000", "\000\000\000\300"));
		tests.add(new testCase("d", -2.0, "\300\000\000\000\000\000\000\000", "\000\000\000\000\000\000\000\300"));

		for (final testCase t : tests) {
			final Object expect = t.expect;
			for (final String[] cas : new String[][] { //
					{ ">" + t.fmt, t.big }, //
					{ "!" + t.fmt, t.big }, //
					{ "<" + t.fmt, t.lil } //
			}) {
				final String xfmt = cas[0];
				final String arg = cas[1];

				final Object o = Struct.unpack(xfmt, arg)[0];
				assertEquals(expect.toString(), o.toString());
			}
		}

	}
}
