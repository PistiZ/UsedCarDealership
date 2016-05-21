package hu.pistiz.cars.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {

	private final static Path mainDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles");
	private final static Path dealershipDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "dealership");
	private final static Path carDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "cars");
	private final static Path carsForSaleDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "cars", "forsale");
	private final static Path soldCarsDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "cars", "sold");
	private final static Path personDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "person");
	private final static Path imageDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "images");
	private final static Path logDir = Paths.get(System.getProperty("user.home"), ".UsedCarDealershipFiles", "log");

	public static Path getMainDir() {
		return mainDir;
	}

	public static Path getDealershipDir() {
		return dealershipDir;
	}

	public static Path getCarDir() {
		return carDir;
	}

	public static Path getCarsForSaleDir() {
		return carsForSaleDir;
	}

	public static Path getSoldCarsDir() {
		return soldCarsDir;
	}

	public static Path getPersonDir() {
		return personDir;
	}

	public static Path getImageDir() {
		return imageDir;
	}

	public static Path getLogDir() {
		return logDir;
	}
}