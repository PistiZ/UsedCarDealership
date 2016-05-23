package hu.pistiz.cars.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Használtautó-kereskedést reprezentáló osztály.
 *
 * Az osztály példányán keresztül a használtautók kereskedése reprezentálódik.
 */
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

	/**
	 * Paraméter nélküli konstruktor, mely a nevet <code>null</code>-ra állítja.
	 */
	public Dealership() {
		this(null);
	}

	/**
	 * A <code>Dealership</code> osztály fő konstruktora.
	 *
	 * A kereskedés címe és a cégnév <code>null</code>-ra állítódik, tulajdonosként egy új
	 * <a href="Person.html">Person</a> objektum jön létre, az egyenlegek pedig 0-ra inicializálódnak.
	 *
	 * @param name a létrehozni kívánt kereskedése neve
	 */
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
	}

	/**
	 * Visszadja a kereskedés nevét.
	 *
	 * @return a kereskedés neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * Beállítja a kereskedés nevét.
	 *
	 * @param name a kereskedés neve
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Visszadja a kereskedés címét.
	 *
	 * @return a kereskedés címe
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Beállítja a kereskedés címét.
	 *
	 * @param address a kereskedés címe
	 */
	public void setAddress(String address) {
		this.address= address;
	}

	/**
	 * Visszadja a kereskedés cégnevét.
	 *
	 * @return a kereskedés cégneve
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Beállítja a kereskedés cégnevét.
	 *
	 * @param companyName a kereskedés cégneve
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Visszadja a kereskedés tulajdonosát.
	 *
	 * @return a kereskedés tulajdonosa
	 */
	public Person getOwner() {
		return owner;
	}

	/**
	 * Beállítja a kereskedés tulajdonosát.
	 *
	 * @param owner a kereskedés tulajdonosa
	 */
	public void setOwner(Person owner) {
		this.owner = owner;
	}

	/**
	 * Visszadja a kereskedés kezdőtőkéjét.
	 *
	 * @return a kereskedés kezdőtőkéje
	 */
	public long getStartingMoney() {
		return startingMoney;
	}

	/**
	 * Beállítja a kereskedés kezdőtőkéjét.
	 *
	 * @param startingMoney a kereskedés kezdőtőkéje
	 */
	public void setStartingMoney(long startingMoney) {
		this.startingMoney = startingMoney;
	}

	/**
	 * Visszadja a kereskedés bevételét.
	 *
	 * @return a kereskedés bevétele
	 */
	public long getIncome() {
		return income;
	}

	/**
	 * Beállítja a kereskedés bevételét.
	 *
	 * @param income a kereskedés bevétele
	 */
	public void setIncome(long income) {
		this.income = income;
	}

	/**
	 * Visszadja a kereskedés profiját.
	 *
	 * @return a kereskedés profitja
	 */
	public long getProfit() {
		return profit;
	}

	/**
	 * Beállítja a kereskedés profiját.
	 *
	 * @param profit a kereskedés profitja
	 */
	public void setProfit(long profit) {
		this.profit = profit;
	}

	/**
	 * Visszadja a kereskedésben eddig eladott autók számát.
	 *
	 * @return az eddig eladott autók száma
	 */
	public long getSoldCars() {
		return soldCars;
	}

	/**
	 * Beállítja a kereskedésben eddig eladott autók számát.
	 *
	 * @param soldCars az eddig eladott autók száma
	 */
	public void setSoldCars(long soldCars) {
		this.soldCars = soldCars;
	}

	/**
	 * Visszadja a kereskedés aktuális egyenlegét.
	 *
	 * @return a kereskedés aktuális egyenlege
	 */
	public long getRemainder() {
		return remainder;
	}

	/**
	 * Beállítja a kereskedés aktuális egyenlegét.
	 *
	 * @param remainder a kereskedés aktuális egyenlege
	 */
	public void setRemainder(long remainder) {
		this.remainder = remainder;
	}

	/**
	 * Növeli a kereskedés bevételét.
	 *
	 * @param increment a növekmény
	 */
	public void incrementIncome(long increment) {
		this.income += increment;
	}

	/**
	 * Növeli a kereskedés profitját.
	 *
	 * @param increment a növekmény
	 */
	public void incrementProfit(long increment) {
		this.profit += increment;
	}

	/**
	 * Növeli az adott kereskedés aktuális egyenlegét.
	 *
	 * @param increment a növekmény
	 */
	public void incrementRemainder(long increment) {
		this.remainder += increment;
	}

	/**
	 * Növeli az adott kereskedés kezdőtőkéjét.
	 *
	 * @param increment a növekmény
	 */
	public void incrementStartingMoney(long increment) {
		this.startingMoney += increment;
	}

	/**
	 * Csökkenti az adott kereskedés aktuális egyenlegét.
	 *
	 * @param decrease a csökkenés
	 */
	public void decreaseRemainder(long decrease) {
		this.remainder -= decrease;
	}

	/**
	 * Növeli az adott kereskedésben eladott autók számát eggyel.
	 *
	 */
	public void saleCar() {
		this.soldCars += 1;
	}

}