package hu.pistiz.cars.model;

import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

	private StringProperty name;
	private Person owner;

	private long income;
	private List<Car> carsForSale;
	private List<Car> soldCars;

	public Dealership() {
		this(null, null);
	}

	public Dealership(String name, Person owner) {
		this.name.set(name);
		this.owner = owner;
		this.income = 0;
		this.carsForSale = new ArrayList<Car>();
		this.soldCars = new ArrayList<Car>();
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public List<Car> getCarsForSale() {
		return carsForSale;
	}

	public void setCarsForSale(List<Car> carsForSale) {
		this.carsForSale = carsForSale;
	}

	public List<Car> getSoldCars() {
		return soldCars;
	}

	public void setSoldCars(List<Car> soldCars) {
		this.soldCars = soldCars;
	}
}
