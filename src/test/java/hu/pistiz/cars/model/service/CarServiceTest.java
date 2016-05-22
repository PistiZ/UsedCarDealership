// CHECKSTYLE:OFF
package hu.pistiz.cars.model.service;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Car;
import hu.pistiz.cars.util.PathUtil;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CarServiceTest {

	private static CarService service;

	@BeforeClass
	public static void beforeTestClass() {
		service = new CarService();
	}

	@Before
	public void setUp() {
		if (service == null)
			Assert.fail("The dealershipService become unavailable before the test");
	}

	@Test
	public void testPriceToString() throws Exception {
		assertEquals("1000000 Ft", service.priceToString(1000000));
	}

	@Test
	public void testKmToString() throws Exception {
		assertEquals("200000 km", service.kmToString(200000));
	}

	@Test
	public void testCreateCar() throws Exception {
		assertNull(service.createCar().getBrand());
		assertNull(service.createCar().getModel());
	}

	@Test
	public void testFindIfSoldCarPresents() throws Exception {
		Car car1 = service.createCar();
		car1.setLicensePlateNumber("ASD-123");

		Car car2 = service.createCar();
		car1.setLicensePlateNumber("QWE-789");

		List<String> fileNames = Arrays.asList(car1.getLicensePlateNumber() + ".xml", car2.getLicensePlateNumber() + ".xml");

		assertTrue(service.findIfSoldCarPresents(car1.getLicensePlateNumber() + ".xml", fileNames));
		assertTrue(service.findIfSoldCarPresents(car2.getLicensePlateNumber() + ".xml", fileNames));
	}
}