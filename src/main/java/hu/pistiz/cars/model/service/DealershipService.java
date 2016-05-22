package hu.pistiz.cars.model.service;

import hu.pistiz.cars.model.Dealership;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A kereskedéssel kapcsolatos üzleti logikát tartalmazó osztály.
 */
public class DealershipService {

	/**
	 * A naplózást végző logger.
	 */
	public static Logger logger = LoggerFactory.getLogger(DealershipService.class);

	/**
	 * Paraméter nélküli konstruktor.
	 */
	public DealershipService() {
	}

	/**
	 * Növeli az adott kereskedés bevételét.
	 *
	 * @param dealership a kereskedés
	 * @param increment a növekmény
	 */
	public void incrementIncome(Dealership dealership, long increment) {
		dealership.incrementIncome(increment);
		logger.info("Bevétel növelődött: " + increment + " Ft-tal.");
	}

	/**
	 * Növeli az adott kereskedés profiját.
	 *
	 * @param dealership a kereskedés
	 * @param increment a növekmény
	 */
	public void incrementProfit(Dealership dealership, long increment) {
		dealership.incrementProfit(increment);
		logger.info("Profit növelődött: " + increment + " Ft-tal.");
	}

	/**
	 * Növeli az adott kereskedés kezdőtőkéjét.
	 *
	 * @param dealership a kereskedés
	 * @param increment a növekmény
	 */
	public void incrementStartingMoney(Dealership dealership, long increment) {
		dealership.incrementStartingMoney(increment);
		logger.info("Kezdőtőke növelődött: " + increment + " Ft-tal.");
	}

	/**
	 * Növeli az adott kereskedés aktuális egyenlegét.
	 *
	 * @param dealership a kereskedés
	 * @param increment a növekmény
	 */
	public void incrementRemainder(Dealership dealership, long increment) {
		dealership.incrementRemainder(increment);
		logger.info("Aktuális egyenleg növelődött: " + increment + " Ft-tal.");
	}

	/**
	 * Csökkenti az adott kereskedés aktuális egyenlegét.
	 *
	 * @param dealership a kereskedés
	 * @param decrease a csökkenés
	 */
	public void decreaseRemainder(Dealership dealership, long decrease) {
		dealership.decreaseRemainder(decrease);
		logger.info("Aktuális egyenleg csökkent: " + decrease + " Ft-tal.");
	}

	/**
	 * Növeli az adott kereskedésben eladott autók számát.
	 *
	 * @param dealership a kereskedés
	 */
	public void saleCar(Dealership dealership) {
		dealership.saleCar();
		logger.info("Eladott autók száma eggyel nőtt.");
	}

}
