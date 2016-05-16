package hu.pistiz.cars.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtil {

	private final static Path mainDir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles");
	private final static Path dealershipDir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "dealership");
	private final static Path carDir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "cars");
	private final static Path carsForSaleDir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "cars", "forsale");
	private final static Path soldCarsDir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "cars", "sold");
	private final static Path personDir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "person");
	private final static Path imageDir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", "images");

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
}