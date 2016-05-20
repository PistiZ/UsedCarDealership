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
	private long startingMoney;
	@XmlElement
	private long income;
	@XmlElement
	private long profit;
	@XmlElement
	private long soldCars;
	@XmlElement
	private long remainder;

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
		this.startingMoney = 0;
		this.income = 0;
		this.profit = 0;
		this.soldCars = 0;
		this.remainder = 0;
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

	public long getStartingMoney() {
		return startingMoney;
	}

	public void setStartingMoney(long startingMoney) {
		this.startingMoney = startingMoney;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public long getProfit() {
		return profit;
	}

	public void setProfit(long profit) {
		this.profit = profit;
	}

	public long getSoldCars() {
		return soldCars;
	}

	public long getRemainder() {
		return remainder;
	}

	public void setRemainder(long remainder) {
		this.remainder = remainder;
	}

	public void incrementIncome(long increment) {
		this.income += increment;
	}

	public void incrementProfit(long increment) {
		this.profit += increment;
	}

	public void incrementRemainder(long increment) {
		this.remainder += increment;
	}

	public void incrementStartingMoney(long increment) {
		this.startingMoney += increment;
	}

	public void decreaseRemainder(long decrease) {
		this.remainder -= decrease;
	}

	public void saleCar() {
		this.soldCars += 1;
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