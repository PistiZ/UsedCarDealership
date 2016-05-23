package hu.pistiz.cars.model.service;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Az autókkal kapcsolatos üzleti logikát tartalmazó osztály.
 */
public class CarService {

	/**
	 * A naplózást végző logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(CarService.class);

	/**
	 * Paraméter nélküli konstruktor.
	 */
	public CarService() {
	}

	/**
	 * Új <code>Car</code> objektumot hoz létre.
	 *
	 * @return az újonnan létrehozott <code>Car</code> objektum
	 */
	public Car createCar() {
		Car car = new Car();
		logger.info("Új autó-objektum készült.");
		return car;
	}

	/**
	 * Meghatározza, hogy a keresett rendszám megtalálható-e a rendszámok listájában.
	 *
	 * @param licensePlateNumber a keresett rendszám
	 * @param fileNames a rendszámok listája
	 * @return találat esetén igaz, ellenben hamis
	 */
	public boolean findIfSoldCarPresents(String licensePlateNumber, List<String> fileNames) {
		logger.info("Rendszám-keresés történt.");
		return fileNames.contains(licensePlateNumber);
	}

	/**
	 * Adott rendszámú <code>Car</code> objektumot tölt be XML-ből.
	 *
	 * @param handler a DAO-t kezelő osztály, amely a betöltést végzi
	 * @param licensePlateNumber a keresett rendszám
	 * @return a kért <code>Car</code> objektum
	 */
	public Car getSoldCar(CarDealershipHandler handler, String licensePlateNumber) {
		logger.info("Eladott autó lekérése történt.");
		return handler.getCarDAO().getSoldCarByLPN(licensePlateNumber);
	}

	/**
	 * Adott rendszámú autó visszavétele a megadott visszavásárlási áron.
	 *
	 * @param handler a DAO-t kezelő osztály, amely a betöltést végzi
	 * @param licensePlateNumber a keresett rendszám
	 * @param price visszavásárlási ár
	 * @return a visszavásárolt <code>Car</code> objektum
	 */
	public Car buyBack(CarDealershipHandler handler, String licensePlateNumber, long price) {
		Car buyBackCar = getSoldCar(handler, licensePlateNumber);

		buyBackCar.setPurchasePrice(price);
		handler.getCarDAO().addCarForSale(buyBackCar);
		handler.getCarDAO().removeSoldCar(buyBackCar.getLicensePlateNumber());

		logger.info("Autó visszavásárlásáa történt.");

		return buyBackCar;
	}

	/**
	 * Autó árát szöveggé konvertáló metódus.
	 *
	 * @param price az autó ára
	 * @return az a <code>String</code>, amely az árat tartalmazza Ft-ban
	 */
	public String priceToString(long price) {
		return Long.toString(price) + " Ft";
	}

	/**
	 * Autó kilométeróra-állását szöveggé konvertáló metódus.
	 *
	 * @param km az autó kilométeróra-állása
	 * @return az a <code>String</code>, amely a kilométeróra-állást tartalmazza km-ben
	 */
	public String kmToString(long km) {
		return Long.toString(km) + " km";
	}

}
