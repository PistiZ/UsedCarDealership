package hu.pistiz.cars.util;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.*;

public class YearAdapterTest {

	private static YearAdapter adapter;

	@BeforeClass
	public static void beforeTestClass() {
		adapter = new YearAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		assertEquals(Year.of(2016), adapter.unmarshal("2016"));
	}

	@Test
	public void testMarshal() throws Exception {
		assertEquals("2016", adapter.marshal(Year.of(2016)));
	}
}