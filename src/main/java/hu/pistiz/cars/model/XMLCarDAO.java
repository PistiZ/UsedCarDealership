package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;
import hu.pistiz.cars.util.PathUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XMLCarDAO implements CarDAO {

	Path carsForSaleDir = PathUtil.getCarsForSaleDir();
	Path soldCarsDir = PathUtil.getSoldCarsDir();

	@Override
	public String priceToString(long price) {
		return Long.toString(price) + " Ft";
	}

	@Override
	public String kmToString(long km) {
		return Long.toString(km) + " km";
	}

	@Override
	public void addCarForSale(Car car) throws FileAlreadyExistsException {
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
	public void removeCarByLPN(String licensePlateNumber) {
		File carFile = new File((Paths.get(carsForSaleDir.toString(), licensePlateNumber + ".xml")).toUri());
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