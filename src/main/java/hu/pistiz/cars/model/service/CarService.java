package hu.pistiz.cars.model.service;

import hu.pistiz.cars.CarDealershipHandler;
import hu.pistiz.cars.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class CarService {

	public static Logger logger = LoggerFactory.getLogger(CarService.class);

	public CarService() {
	}

	public Car createCar() {
		Car car = new Car();
		logger.info("Új autó-objektum készült.");
		return car;
	}

	public boolean findIfSoldCarPresents(String licensePlateNumber, List<String> fileNames) {
		logger.info("Rendszám-keresés történt.");
		return fileNames.contains(licensePlateNumber);
	}

	public Car getSoldCar(CarDealershipHandler handler, String licensePlateNumber) {
		logger.info("Eladott autó lekérése történt.");
		return handler.getCarDAO().getSoldCarByLPN(licensePlateNumber);
	}

	public Car buyBack(CarDealershipHandler handler, String licensePlateNumber, long price) {
		Car buyBackCar = getSoldCar(handler, licensePlateNumber);

		buyBackCar.setPurchasePrice(price);
		handler.getCarDAO().addCarForSale(buyBackCar);
		handler.getCarDAO().removeSoldCar(buyBackCar.getLicensePlateNumber());

		logger.info("Autó visszavásárlásáa történt.");

		return buyBackCar;
	}

	public String priceToString(long price) {
		return Long.toString(price) + " Ft";
	}

	public String kmToString(long km) {
		return Long.toString(km) + " km";
	}

}