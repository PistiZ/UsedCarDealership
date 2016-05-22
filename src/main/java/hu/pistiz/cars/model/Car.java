package hu.pistiz.cars.model;

import java.time.Year;

import javax.xml.bind.annotation.*;

/**
 * Autót reprezentáló osztály.
 *
 * Az osztály példányaival dolgozik a program a kereskedés kezelése során.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Car {

	@XmlElement
	private String brand;
	@XmlElement
	private String model;
	@XmlElement
	private String variant;
	@XmlElement
	private String licensePlateNumber;
	@XmlElement
	private Year date;
	@XmlElement
	private long purchasePrice;
	@XmlElement
	private long salePrice;
	@XmlElement
	private long km;
	@XmlElement
	private Fuel fuel;
	@XmlElement
	private Condition condition;
	@XmlElement
	private String description;

	/**
	 * Paraméter nélküli konstruktor, mely a márkát és a modellt <code>null</code>-ra állítja.
	 */
	public Car() {
		this(null, null);
	}

	/**
	 * A <code>Car</code> osztály fő konstruktora.
	 *
	 * @param brand a létrehozni kívánt autó márkája
	 * @param model a létrehozni kívánt autó modellje
	 */
	public Car(String brand, String model) {
		this.brand = brand;
		this.model = model;
	}

	/**
	 * Visszadja az autó márkáját.
	 *
	 * @return az autó márkája
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Beállítja az autó márkáját.
	 *
	 * @param brand az autó márkája
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Visszadja az autó modelljét.
	 *
	 * @return az autó modellje
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Beállítja az autó modelljét.
	 *
	 * @param model az autó modellje
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Visszadja az autó típusát.
	 *
	 * @return az autó típusa
	 */
	public String getVariant() {
		return variant;
	}

	/**
	 * Beállítja az autó típusát.
	 *
	 * @param variant az autó típusa
	 */
	public void setVariant(String variant) {
		this.variant = variant;
	}

	/**
	 * Visszadja az autó rendszámát.
	 *
	 * @return az autó rendszáma
	 */
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	/**
	 * Beállítja az autó rendszámát.
	 *
	 * @param licensePlateNumber az autó rendszáma
	 */
	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	/**
	 * Visszadja az autó évjáratát.
	 *
	 * @return az autó évjárata
	 */
	public Year getDate() {
		return date;
	}

	/**
	 * Beállítja az autó évjáratát.
	 *
	 * @param date az autó évjárata
	 */
	public void setDate(Year date) {
		this.date = date;
	}

	/**
	 * Visszadja az autó vételárát.
	 *
	 * @return az autó vételára
	 */
	public long getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Beállítja az autó vételárát.
	 *
	 * @param purchasePrice az autó vételára
	 */
	public void setPurchasePrice(long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * Visszadja az árat, amennyiért az autó el lett adva.
	 *
	 * @return az autó eladási ára
	 */
	public long getSalePrice() {
		return salePrice;
	}

	/**
	 * Beállítja az autó eladási árát.
	 *
	 * @param salePrice az autó eladási ára
	 */
	public void setSalePrice(long salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * Visszadja az autó kilométeróra-állását.
	 *
	 * @return az autó kilométeróra-állása
	 */
	public long getKm() {
		return km;
	}

	/**
	 * Beállítja az autó kilométeróra-állását.
	 *
	 * @param km az autó kilométeróra-állása
	 */
	public void setKm(long km) {
		this.km = km;
	}

	/**
	 * Visszadja az autó üzemanyag-típusát.
	 *
	 * @return az autó üzemanyag-típusa
	 */
	public Fuel getFuel() {
		return fuel;
	}

	/**
	 * Beállítja az autó üzemanyag-típusát.
	 *
	 * @param fuel az autó üzemanyag-típusa
	 */
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	/**
	 * Visszadja az autó állapot-típusát.
	 *
	 * @return az autó állapot-típusa
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * Beállítja az autó állapot-típusát.
	 *
	 * @param condition az autó állapot-típusa
	 */
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	/**
	 * Visszadja az autó bő leírását.
	 *
	 * @return az autó bő leírása
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Beállítja az autó bő leírását.
	 *
	 * @param description az autó bő leírása
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}