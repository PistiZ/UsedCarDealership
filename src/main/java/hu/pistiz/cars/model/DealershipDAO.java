package hu.pistiz.cars.model;

import java.io.FileNotFoundException;
import java.util.List;

public interface DealershipDAO {

	void addDealership(Dealership dealership);

	//Dealership getDealershipByName(String name) throws FileNotFoundException;

	Dealership getDealership() throws FileNotFoundException;

	List<Car> getAllCars();

	void addCarForSale(Car car);

	void addSoldCar(Car car);

	void removeCarForSale(Car car);

	void removeSoldCar(Car car);

}
