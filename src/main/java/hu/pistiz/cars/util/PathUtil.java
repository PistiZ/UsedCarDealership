package hu.pistiz.cars.util;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A programban használt fájlrendszerbeli {@link Path} útvonalakat tároló osztály.
 */
public class PathUtil {

	/**
	 * A fő, rejtett mappa útvonala a felhasználó könyvtárában.
	 */
	private final static Path mainDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles");
	/**
	 * A kereskedést tartalmazó mappa.
	 */
	private final static Path dealershipDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "dealership");
	/**
	 * Az autók mappája.
	 */
	private final static Path carDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "cars");
	/**
	 * Az eladó autók mappája.
	 */
	private final static Path carsForSaleDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "cars", "forsale");
	/**
	 * Az eladott autók mappája.
	 */
	private final static Path soldCarsDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "cars", "sold");
	/**
	 * A tulajdonos mappája.
	 */
	private final static Path personDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "person");
	/**
	 * A képek mappája.
	 */
	private final static Path imageDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "images");
	/**
	 * A naplózás mappája.
	 */
	private final static Path logDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "log");

	/**
	 * A program által létrehozott fő mappa útvonalát adja meg.
	 *
	 * @return a program által létrehozott fő mappa útvonala
	 */
	public static Path getMainDir() {
		return mainDir;
	}

	/**
	 * A kereskedés mappájának útvonalát adja meg.
	 *
	 * @return a kereskedés mappájának útvonala
	 */
	public static Path getDealershipDir() {
		return dealershipDir;
	}

	/**
	 * Az autók mappájának útvonalát adja meg.
	 *
	 * @return az autók mappájának útvonala
	 */
	public static Path getCarDir() {
		return carDir;
	}

	/**
	 * Az eladó autók mappájának útvonalát adja meg.
	 *
	 * @return az eladó autók mappájának útvonala
	 */
	public static Path getCarsForSaleDir() {
		return carsForSaleDir;
	}

	/**
	 * Az eladott autók mappájának útvonalát adja meg.
	 *
	 * @return az eladott autók mappájának útvonala
	 */
	public static Path getSoldCarsDir() {
		return soldCarsDir;
	}

	/**
	 * A tulajdonos mappájának útvonalát adja meg.
	 *
	 * @return a tulajdonos mappájának útvonala
	 */
	public static Path getPersonDir() {
		return personDir;
	}

	/**
	 * A képek mappájának útvonalát adja meg.
	 *
	 * @return a képek mappájának útvonala
	 */
	public static Path getImageDir() {
		return imageDir;
	}

	/**
	 * A naplózás mappájának útvonalát adja meg.
	 *
	 * @return a naplózás mappájának útvonala
	 */
	public static Path getLogDir() {
		return logDir;
	}
}