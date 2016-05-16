package hu.pistiz.cars.model;

import javafx.collections.ObservableList;

import java.nio.file.FileAlreadyExistsException;

public interface CarDAO {

	String priceToString(long price);

	String kmToString(long km);

	void addCarForSale(Car car) throws FileAlreadyExistsException;

	void addSoldCar(Car car);

	ObservableList<Car> getCarsForSale();

	ObservableList<Car> getSoldCars();

	void updateCar(Car car);

	void removeCarByLPN(String licensePlateNumber);

}