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

public class XMLCarDAO implements CarDAO {

	public Path carsForSaleDir = PathUtil.getCarsForSaleDir();
	public Path soldCarsDir = PathUtil.getSoldCarsDir();

	public static Logger logger = LoggerFactory.getLogger(XMLCarDAO.class);

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

	/*@Override
	public boolean findIfSoldCarPresents(String licensePlateNumber) {
		File carFile = new File((Paths.get(soldCarsDir.toString(), licensePlateNumber + ".xml").toUri()));
		return carFile.exists();
	}*/

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

	@Override
	public List<String> getSoldCarsList() {
		File carFile = new File((Paths.get(soldCarsDir.toString()).toUri()));
		logger.info("Eladott autók listázása.");
		return Arrays.asList(carFile.list());
	}

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

	@Override
	public void removeCarForSale(String licensePlateNumber) {
		File carFile = new File((Paths.get(carsForSaleDir.toString(), licensePlateNumber + ".xml")).toUri());
		if (carFile.exists())
			carFile.delete();
		logger.info(carFile.toURI().toString() + " eladó autó XML fájlja törölve.");
	}

	@Override
	public void removeSoldCar(String licensePlateNumber) {
		File carFile = new File((Paths.get(soldCarsDir.toString(), licensePlateNumber + ".xml")).toUri());
		if (carFile.exists())
			carFile.delete();
		logger.info(carFile.toURI().toString() + " eladott autó XML fájlja törölve.");
	}

	/*@Override
	public void carToXML(Car car, String fileName) {
		Path dir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles");
		Path path = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", fileName);
		File saveFile = path.toFile();

		if (!new File(dir.toUri()).exists()) {
			try {
				Files.createDirectory(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			OutputStream os = new FileOutputStream(saveFile);
			JAXBUtil.toXML(car, os);
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}*/

}