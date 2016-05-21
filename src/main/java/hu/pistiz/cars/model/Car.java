package hu.pistiz.cars.model;

import java.time.Year;

import javax.xml.bind.annotation.*;

/**
 * Gépjárművet reprezentáló osztály.
 * Az osztály példányai a kereskedésekben használhatóak fel.
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

	public Car() {
		this(null, null);
	}

	/**
	 * Constructor with some initial data.
	 *
	 */
	public Car(String brand, String model) {
		this.brand = brand;
		this.model = model;
		//this.licensePlateNumber = "ASD-123";

		/*this.variant = new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<>(Year.of(2016));
		this.purchasePrice = new SimpleLongProperty(1000000);
		this.salePrice = new SimpleLongProperty(1100000);
		this.km = new SimpleLongProperty(200000);
		this.fuel = new SimpleObjectProperty<>(Fuel.benzin);
		this.condition = new SimpleObjectProperty<>(Condition.megkímélt);
		this.description = new SimpleStringProperty("Üres leírás");*/
		//this.image = new SimpleObjectProperty<>()
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public Year getDate() {
		return date;
	}

	public void setDate(Year date) {
		this.date = date;
	}

	public long getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(long salePrice) {
		this.salePrice = salePrice;
	}

	public long getKm() {
		return km;
	}

	public void setKm(long km) {
		this.km = km;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public String getBrand() {
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

	public Fuel getFuel() {
		return fuel.get();
	}

	public ObjectProperty<Fuel> fuelProperty() {
		return fuel;
	}

	public void setFuel(Fuel fuel) {
		this.fuel.set(fuel);
	}

	public Condition getCondition() {
		return condition.get();
	}

	public ObjectProperty<Condition> conditionProperty() {
		return condition;
	}

	public void setCondition(Condition condition) {
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
	}*/
}