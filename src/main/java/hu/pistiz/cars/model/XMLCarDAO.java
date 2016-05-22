package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;
import hu.pistiz.cars.util.PathUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="Car.html">Car</a> objektumok XML-ben történő kezelését végző osztály.
 */
public class XMLCarDAO implements CarDAO {

	/**
	 * Az eladó autók mappája
	 */
	public Path carsForSaleDir = PathUtil.getCarsForSaleDir();
	/**
	 * Az eladott autók mappája
	 */
	public Path soldCarsDir = PathUtil.getSoldCarsDir();

	/**
	 * A naplózást végző logger.
	 */
	public static Logger logger = LoggerFactory.getLogger(XMLCarDAO.class);

	/**
	 * Új autó felvételekor a paraméterként kapott objektumot XML-be menti.
	 *
	 * @param car a menteni kívánt autó
	 * @throws FileAlreadyExistsException ha az autó már létezik az adatbázisban
	 */
	@Override
	public void newCar(Car car) throws FileAlreadyExistsException {
		try {
			File carForSaleFile = new File((Paths.get(carsForSaleDir.toString(), car.getLicensePlateNumber().toUpperCase() + ".xml")).toUri());
			File soldCarFile = new File((Paths.get(soldCarsDir.toString(), car.getLicensePlateNumber().toUpperCase() + ".xml")).toUri());
			boolean fileCreated = false;
			if (carForSaleFile.exists()) {
				logger.warn("Már fel lett véve eladó autóként!");
				throw new FileAlreadyExistsException("Már fel lett véve eladó autóként!");
			}
			else if (soldCarFile.exists()) {
				logger.warn("Már fel lett véve eladott autóként!");
				throw new FileAlreadyExistsException("Már fel lett véve eladott autóként!");
			}
			else
				try {
					fileCreated = carForSaleFile.createNewFile();
				} catch (IOException e) {
					logger.error("Új eladó autó XML fájl készítése sikertelen", e);
				}

			if (fileCreated) {
				JAXBUtil.toXML(car, new FileOutputStream(carForSaleFile));
				logger.info(car.getLicensePlateNumber() + " új autó felvéve.");
			}
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
	}

	/**
	 * A paraméterként kapott objektumot eladó autóként veszi fel.
	 *
	 * Visszavásárláskor használatos.
	 *
	 * @param car a menteni kívánt autó
	 */
	@Override
	public void addCarForSale(Car car) {
		try {
			File carFile = new File((Paths.get(carsForSaleDir.toString(), car.getLicensePlateNumber() + ".xml")).toUri());
			boolean fileCreated = false;
			try {
				fileCreated = carFile.createNewFile();
			} catch (IOException e) {
				logger.error("Új visszavásárolt eladó autó XML fájl készítése sikertelen", e);
			}

			if (fileCreated) {
				JAXBUtil.toXML(car, new FileOutputStream(carFile));
				logger.info(car.getLicensePlateNumber() + " eladó autó felvéve.");
			}
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
	}

	/**
	 * A paraméterként kapott objektumot eladott autóként veszi fel.
	 *
	 * @param car a menteni kívánt autó
	 */
	@Override
	public void addSoldCar(Car car) {
		try {
			File carFile = new File((Paths.get(soldCarsDir.toString(), car.getLicensePlateNumber() + ".xml")).toUri());
			boolean fileCreated = false;
			try {
				fileCreated = carFile.createNewFile();
			} catch (IOException e) {
				logger.error("Új eladott autó XML fájl készítése sikertelen", e);
			}

			if (fileCreated) {
				JAXBUtil.toXML(car, new FileOutputStream(carFile));
				logger.info(car.getLicensePlateNumber() + " eladott autó felvéve.");
			}
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
	}

	/**
	 * Eladott autót tölt be rendszám alapján.
	 *
	 * @param licensePlateNumber a betölteni kívánt autó rendszáma
	 * @return a betöltött eladott autó
	 */
	@Override
	public Car getSoldCarByLPN(String licensePlateNumber) {
		Car car = new Car();
		File carFile = new File((Paths.get(soldCarsDir.toString(), licensePlateNumber + ".xml").toUri()));
		try {
			car = JAXBUtil.fromXML(Car.class,  new FileInputStream(carFile));
			logger.info("Új " + car.getLicensePlateNumber() + " rendszámú "
					+ car.getBrand() + " " + car.getModel() + " eladott gépjármű betöltése XML-ből sikeres.");
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
		return car;
	}

	/**
	 * Az eladó autók listáját kéri le.
	 *
	 * @return az eladó autók listája
	 */
	@Override
	public ObservableList<Car> getCarsForSale() {
		ObservableList<Car> carsForSale = FXCollections.observableArrayList();
		File carFile = new File((Paths.get(carsForSaleDir.toString()).toUri()));
		String[] fileNames = carFile.list();
		for (String file : fileNames) {
			try {
				Car car = JAXBUtil.fromXML(Car.class,  new FileInputStream(new File((Paths.get(carsForSaleDir.toString(), file)).toUri())));
				carsForSale.add(car);
			} catch (JAXBException e) {
				logger.error("Sikertelen JAXB-művelet", e);
			} catch (FileNotFoundException e) {
				logger.error("A fájl nem található", e);
			}
		}
		return carsForSale;
	}

	/**
	 * Az eladott autók listáját kéri le.
	 *
	 * @return az eladott autók listája
	 */
	@Override
	public ObservableList<Car> getSoldCars() {
		ObservableList<Car> soldCars = FXCollections.observableArrayList();
		File carFile = new File((Paths.get(soldCarsDir.toString()).toUri()));
		String[] fileNames = carFile.list();
		for (String file : fileNames) {
			try {
				Car car = JAXBUtil.fromXML(Car.class,  new FileInputStream(new File((Paths.get(soldCarsDir.toString(), file)).toUri())));
				soldCars.add(car);
			} catch (JAXBException e) {
				logger.error("Sikertelen JAXB-művelet", e);
			} catch (FileNotFoundException e) {
				logger.error("A fájl nem található", e);
			}
		}
		return soldCars;
	}

	/**
	 * Az eladó autók fájlneveinek listáját adja meg.
	 *
	 * @return az eladó autók fájlnevei
	 */
	@Override
	public List<String> getSoldCarsList() {
		File carFile = new File((Paths.get(soldCarsDir.toString()).toUri()));
		logger.info("Eladott autók listázása.");
		return Arrays.asList(carFile.list());
	}

	/**
	 * A paraméterként kapott autót frissíti XML-ben.
	 *
	 * @param car a frissítendő autó
	 */
	@Override
	public void updateCar(Car car) {
		try {
			File carFile = new File((Paths.get(carsForSaleDir.toString(), car.getLicensePlateNumber() + ".xml")).toUri());
			boolean fileCreated = false;
			if (carFile.exists()) {
				carFile.delete();
				try {
					fileCreated = carFile.createNewFile();
				} catch (IOException e) {
					logger.error(carFile.toURI().toString() + " fájl létrehozása sikertelen", e);
				}
			}
			else {
				try {
					fileCreated = carFile.createNewFile();
				} catch (IOException e) {
					logger.error(carFile.toURI().toString() + " fájl létrehozása sikertelen", e);
				}
			}

			if (fileCreated) {
				JAXBUtil.toXML(car, new FileOutputStream(carFile));
				logger.info(car.getLicensePlateNumber() + " rendszámú autó frissítve.");
			}
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
	}

	/**
	 * Eladó autót töröl.
	 *
	 * @param licensePlateNumber a törlendő autó
	 */
	@Override
	public void removeCarForSale(String licensePlateNumber) {
		File carFile = new File((Paths.get(carsForSaleDir.toString(), licensePlateNumber + ".xml")).toUri());
		if (carFile.exists())
			carFile.delete();
		logger.info(carFile.toURI().toString() + " eladó autó XML fájlja törölve.");
	}

	/**
	 * Eladott autót töröl.
	 *
	 * @param licensePlateNumber a törlendő autó
	 */
	@Override
	public void removeSoldCar(String licensePlateNumber) {
		File carFile = new File((Paths.get(soldCarsDir.toString(), licensePlateNumber + ".xml")).toUri());
		if (carFile.exists())
			carFile.delete();
		logger.info(carFile.toURI().toString() + " eladott autó XML fájlja törölve.");
	}

}