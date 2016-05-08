package hu.pistiz.cars.model;

public class VehicleDAOImpl implements VehicleDAO {

	public String priceToString(long price) {
		return Long.toString(price) + " Ft";
	}

	public String kmToString(long km) {
		return Long.toString(km) + " km";
	}

}