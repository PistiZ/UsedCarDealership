package hu.pistiz.cars.model.service;

import hu.pistiz.cars.model.Dealership;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DealershipService {

	public static Logger logger = LoggerFactory.getLogger(DealershipService.class);

	/*public Dealership createDealership(String name) {
		Dealership dealership = new Dealership();
		dealership.setName(name);
		dealership.setOwner(null);
		dealership.setAddress(null);
		dealership.setOwner(new Person());
		dealership.setIncome(0);
		return dealership;
	}*/

	public DealershipService() {
	}

	public void incrementIncome(Dealership dealership, long increment) {
		dealership.incrementIncome(increment);
		logger.info("Bevétel növelődött: " + increment + " Ft-tal.");
	}

	public void incrementProfit(Dealership dealership, long increment) {
		dealership.incrementProfit(increment);
		logger.info("Profit növelődött: " + increment + " Ft-tal.");
	}

	public void incrementStartingMoney(Dealership dealership, long increment) {
		dealership.incrementStartingMoney(increment);
		logger.info("Kezdőtőke növelődött: " + increment + " Ft-tal.");
	}

	public void incrementRemainder(Dealership dealership, long increment) {
		dealership.incrementRemainder(increment);
		logger.info("Aktuális egyenleg növelődött: " + increment + " Ft-tal.");
	}

	public void decreaseRemainder(Dealership dealership, long decrease) {
		dealership.decreaseRemainder(decrease);
		logger.info("Aktuális egyenleg csökkent: " + decrease + " Ft-tal.");
	}

	public void saleCar(Dealership dealership) {
		dealership.saleCar();
		logger.info("Eladott autók száma eggyel nőtt.");
	}

}
