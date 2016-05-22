// CHECKSTYLE:OFF
package hu.pistiz.cars.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class LicensePlateNumberUtilTest {

	@Test
	public void testIsValid() {
		assertTrue(LicensePlateNumberUtil.isValid("ASD-123"));
		assertTrue(LicensePlateNumberUtil.isValid("asd-123"));

		assertFalse(LicensePlateNumberUtil.isValid("ASD-1234"));
		assertFalse(LicensePlateNumberUtil.isValid("ASD123"));
		assertFalse(LicensePlateNumberUtil.isValid("1as-123"));
		assertFalse(LicensePlateNumberUtil.isValid("asd*123"));
		assertFalse(LicensePlateNumberUtil.isValid("asd-qwe"));
	}
}