// CHECKSTYLE:OFF
package hu.pistiz.cars.model;

import javafx.collections.ObservableList;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

/**
 * Autók kezelésére szolgáló DAO interfész.
 */
public interface CarDAO {

	void newCar(Car car) throws FileAlreadyExistsException;

	void addCarForSale(Car car);

	void addSoldCar(Car car);

	Car getSoldCarByLPN(String licensePlateNumber);

	ObservableList<Car> getCarsForSale();

	ObservableList<Car> getSoldCars();

	List<String> getSoldCarsList();

	void updateCar(Car car);

	void removeCarForSale(String licensePlateNumber);

	void removeSoldCar(String licensePlateNumber);

}