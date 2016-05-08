package hu.pistiz.cars.model;

import java.time.Year;

import javafx.beans.property.*;

/**
 * Gépjárművet reprezentáló osztály.
 * Az osztály példányai a kereskedésekben használhatóak fel.
 */
public class Vehicle {

	private StringProperty brand;
	private StringProperty model;
	private StringProperty variant;
	private StringProperty licensePlateNumber;
	private ObjectProperty<Year> date;
	private LongProperty purchasePrice;
	private LongProperty salePrice;
	private LongProperty km;
	private ObjectProperty<Uzemanyag> fuel;
	private ObjectProperty<Allapot> condition;
	private StringProperty description;

	public Vehicle() {
		this(null, null);
	}

	/**
	 * Constructor with some initial data.
	 *
	 */
	public Vehicle(String brand, String model) {
		this.brand = new SimpleStringProperty(brand);
		this.model = new SimpleStringProperty(model);
		this.licensePlateNumber = new SimpleStringProperty("ASD-123");

		this.variant = new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<>(Year.of(2016));
		this.purchasePrice = new SimpleLongProperty(1000000);
		this.salePrice = new SimpleLongProperty(1100000);
		this.km = new SimpleLongProperty(200000);
		this.fuel = new SimpleObjectProperty<>(Uzemanyag.benzin);
		this.condition = new SimpleObjectProperty<>(Allapot.megkímélt);
		this.description = new SimpleStringProperty("Üres leírás");
		//this.image = new SimpleObjectProperty<>()
	}

	public String getBrand() {
		return brand.get();
	}

	public StringProperty brandProperty() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand.set(brand);
	}

	public String getModel() {
		return model.get();
	}

	public StringProperty modelProperty() {
		return model;
	}

	public void setModel(String model) {
		this.model.set(model);
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber.get();
	}

	public StringProperty licensePlateNumberProperty() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber.set(licensePlateNumber);
	}

	public String getVariant() {
		return variant.get();
	}

	public StringProperty variantProperty() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant.set(variant);
	}

	public Year getDate() {
		return date.get();
	}

	public ObjectProperty<Year> dateProperty() {
		return date;
	}

	public void setDate(Year date) {
		this.date.set(date);
	}

	public long getPurchasePrice() {
		return purchasePrice.get();
	}

	public LongProperty purchasePriceProperty() {
		return purchasePrice;
	}

	public void setPurchasePrice(long purchasePrice) {
		this.purchasePrice.set(purchasePrice);
	}

	public long getSalePrice() {
		return salePrice.get();
	}

	public LongProperty salePriceProperty() {
		return salePrice;
	}

	public void setSalePrice(long salePrice) {
		this.salePrice.set(salePrice);
	}

	public long getKm() {
		return km.get();
	}

	public LongProperty kmProperty() {
		return km;
	}

	public void setKm(long km) {
		this.km.set(km);
	}

	public Uzemanyag getFuel() {
		return fuel.get();
	}

	public ObjectProperty<Uzemanyag> fuelProperty() {
		return fuel;
	}

	public void setFuel(Uzemanyag fuel) {
		this.fuel.set(fuel);
	}

	public Allapot getCondition() {
		return condition.get();
	}

	public ObjectProperty<Allapot> conditionProperty() {
		return condition;
	}

	public void setCondition(Allapot condition) {
		this.condition.set(condition);
	}

	public String getDescription() {
		return description.get();
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public void setDescription(String description) {
		this.description.set(description);
	}
}