package hu.pistiz.cars.model;

import javafx.collections.ObservableList;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public interface CarDAO {

	void newCar(Car car) throws FileAlreadyExistsException;

	void addCarForSale(Car car);

	void addSoldCar(Car car);

	//boolean findIfSoldCarPresents(String licensePlateNumber);

	Car getSoldCarByLPN(String licensePlateNumber);

	ObservableList<Car> getCarsForSale();

	ObservableList<Car> getSoldCars();

	List<String> getSoldCarsList();

	void updateCar(Car car);

	void removeCarForSale(String licensePlateNumber);

	void removeSoldCar(String licensePlateNumber);

}