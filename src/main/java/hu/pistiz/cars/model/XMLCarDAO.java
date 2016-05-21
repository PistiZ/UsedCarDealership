package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;
import hu.pistiz.cars.util.PathUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	@Override
	public void newCar(Car car) throws FileAlreadyExistsException {
		try {
			File carForSaleFile = new File((Paths.get(carsForSaleDir.toString(), car.getLicensePlateNumber().toUpperCase() + ".xml")).toUri());
			File soldCarFile = new File((Paths.get(soldCarsDir.toString(), car.getLicensePlateNumber().toUpperCase() + ".xml")).toUri());
			boolean fileCreated = false;
			if (carForSaleFile.exists())
				throw new FileAlreadyExistsException("Már fel lett véve eladó autóként!");
			else if (soldCarFile.exists())
				throw new FileAlreadyExistsException("Már fel lett véve eladott autóként!");
			else
				try {
					fileCreated = carForSaleFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}

			if (fileCreated)
				JAXBUtil.toXML(car, new FileOutputStream(carForSaleFile));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}

			if (fileCreated)
				JAXBUtil.toXML(car, new FileOutputStream(carFile));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}

			if (fileCreated)
				JAXBUtil.toXML(car, new FileOutputStream(carFile));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
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
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
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
			} catch (JAXBException | FileNotFoundException e) {
				e.printStackTrace();
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
			} catch (JAXBException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return soldCars;
	}

	@Override
	public List<String> getSoldCarsList() {
		File carFile = new File((Paths.get(soldCarsDir.toString()).toUri()));
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
					e.printStackTrace();
				}
			}
			else {
				try {
					fileCreated = carFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileCreated)
				JAXBUtil.toXML(car, new FileOutputStream(carFile));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeCarForSale(String licensePlateNumber) {
		File carFile = new File((Paths.get(carsForSaleDir.toString(), licensePlateNumber + ".xml")).toUri());
		if (carFile.exists())
			carFile.delete();
	}

	@Override
	public void removeSoldCar(String licensePlateNumber) {
		File carFile = new File((Paths.get(soldCarsDir.toString(), licensePlateNumber + ".xml")).toUri());
		if (carFile.exists())
			carFile.delete();
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