package hu.pistiz.cars.model.service;

import hu.pistiz.cars.model.Dealership;
import org.junit.*;

import static org.junit.Assert.*;

public class DealershipServiceTest {

	private static DealershipService service;

	@BeforeClass
	public static void beforeTestClass() {
		service = new DealershipService();
	}

	@Before
	public void setUp() {
		if (service == null)
			Assert.fail("The dealershipService become unavailable before the test");
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testIncrementIncome() {
		Dealership dealership = new Dealership();
		dealership.setIncome(5000000);

		service.incrementIncome(dealership, 3000000);
		assertEquals(8000000, dealership.getIncome());

		service.incrementIncome(dealership, 2000000);
		assertEquals(10000000, dealership.getIncome());
	}

	@Test
	public void testIncrementProfit() throws Exception {
		Dealership dealership = new Dealership();
		dealership.setProfit(1500000);

		service.incrementProfit(dealership, 700000);
		assertEquals(2200000, dealership.getProfit());

		service.incrementProfit(dealership, 1800000);
		assertEquals(4000000, dealership.getProfit());
	}

	@Test
	public void testIncrementStartingMoney() throws Exception {
		Dealership dealership = new Dealership();
		dealership.setStartingMoney(3000000);

		service.incrementStartingMoney(dealership, 1000000);
		assertEquals(4000000, dealership.getStartingMoney());

		service.incrementStartingMoney(dealership, 2000000);
		assertEquals(6000000, dealership.getStartingMoney());
	}

	@Test
	public void testIncrementRemainder() throws Exception {
		Dealership dealership = new Dealership();
		dealership.setRemainder(1250000);

		service.incrementRemainder(dealership, 250000);
		assertEquals(1500000, dealership.getRemainder());

		service.incrementRemainder(dealership, 400000);
		assertEquals(1900000, dealership.getRemainder());
	}

	@Test
	public void testDecreaseRemainder() throws Exception {
		Dealership dealership = new Dealership();
		dealership.setRemainder(1250000);

		service.decreaseRemainder(dealership, 250000);
		assertEquals(1000000, dealership.getRemainder());

		service.decreaseRemainder(dealership, 400000);
		assertEquals(600000, dealership.getRemainder());
	}

	@Test
	public void testSaleCar() throws Exception {
		Dealership dealership = new Dealership();
		dealership.setSoldCars(139);

		service.saleCar(dealership);
		assertEquals(140, dealership.getSoldCars());

		service.saleCar(dealership);
		assertEquals(141, dealership.getSoldCars());
	}
}