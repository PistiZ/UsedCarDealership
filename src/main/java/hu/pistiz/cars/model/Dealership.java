package hu.pistiz.cars.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Dealership {

	@XmlElement
	private String name;
	@XmlElement
	private String address;
	@XmlElement
	private String companyName;
	private Person owner;

	@XmlElement
	private long income;
	//private List<Car> carsForSale;
	//private List<Car> soldCars;

	public Dealership() {
		this(null);
	}

	public Dealership(String name) {
		this.name = name;
		this.address = null;
		this.companyName = null;
		this.owner = new Person();
		this.income = 0;
		//this.carsForSale = new ArrayList<Car>();
		//this.soldCars = new ArrayList<Car>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address= address;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	/*public List<Car> getCarsForSale() {
		return carsForSale;
	}

	public void setCarsForSale(List<Car> carsForSale) {
		this.carsForSale = carsForSale;
	}*/

	/*public List<Car> getSoldCars() {
		return soldCars;
	}

	public void setSoldCars(List<Car> soldCars) {
		this.soldCars = soldCars;
	}*/

}