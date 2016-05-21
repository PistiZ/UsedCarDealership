package hu.pistiz.cars.util;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class PathUtilTest {

	@Test
	public void testGetMainDir() throws Exception {
		assertEquals(Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles"), PathUtil.getMainDir());
	}

	@Test
	public void testGetDealershipDir() throws Exception {
		assertEquals(Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "dealership"), PathUtil.getDealershipDir());
	}

	@Test
	public void testGetCarDir() throws Exception {
		assertEquals(Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "cars"), PathUtil.getCarDir());
	}

	@Test
	public void testGetCarsForSaleDir() throws Exception {
		assertEquals(Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "cars", "forsale"), PathUtil.getCarsForSaleDir());
	}

	@Test
	public void testGetSoldCarsDir() throws Exception {
		assertEquals(Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "cars", "sold"), PathUtil.getSoldCarsDir());
	}

	@Test
	public void testGetPersonDir() throws Exception {
		assertEquals(Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "person"), PathUtil.getPersonDir());
	}

	@Test
	public void testGetImageDir() throws Exception {
		assertEquals(Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "images"), PathUtil.getImageDir());
	}
}