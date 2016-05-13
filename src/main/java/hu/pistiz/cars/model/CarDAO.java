package hu.pistiz.cars.model;

import java.io.OutputStream;

public interface CarDAO {

	String priceToString(long price);

	String kmToString(long km);

	void carToXML(Car car, String fileName);

}