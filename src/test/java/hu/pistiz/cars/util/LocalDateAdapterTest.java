package hu.pistiz.cars.util;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LocalDateAdapterTest {

	private static LocalDateAdapter adapter;

	@BeforeClass
	public static void beforeTestClass() {
		adapter = new LocalDateAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		assertEquals(2016, adapter.unmarshal("2016-05-21").getYear());
		assertEquals(5, adapter.unmarshal("2016-05-21").getMonthValue());
		assertEquals(21, adapter.unmarshal("2016-05-21").getDayOfMonth());
	}

	@Test
	public void testMarshal() throws Exception {
		assertEquals("2016-05-21", adapter.marshal(LocalDate.of(2016, 5, 21)));
	}
}