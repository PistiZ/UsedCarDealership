package hu.pistiz.cars.model;

import java.time.Year;

import javafx.beans.property.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * Gépjárművet reprezentáló osztály.
 * Az osztály példányai a kereskedésekben használhatóak fel.
 */
public class Vehicle {

	private StringProperty brand;
	private StringProperty model;
	private StringProperty variant;
	private ObjectProperty<Year> date;
	private ObjectProperty<Money> purchasePrice;
	private ObjectProperty<Money> salePrice;
	private LongProperty km;
	private ObjectProperty<Uzemanyag> fuel;
	private ObjectProperty<Allapot> condition;
	private StringProperty description;
	//private ObjectProperty<Image> image;

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

		this.variant = new SimpleStringProperty("");
		this.date = new SimpleObjectProperty<>(Year.of(2016));
		this.purchasePrice = new SimpleObjectProperty<>(Money.of(CurrencyUnit.of("HUF"), 1000000));
		this.salePrice = new SimpleObjectProperty<>(Money.of(CurrencyUnit.of("HUF"), 1100000));
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

	public Money getPurchasePrice() {
		return purchasePrice.get();
	}

	public ObjectProperty<Money> purchasePriceProperty() {
		return purchasePrice;
	}

	public void setPurchasePrice(Money purchasePrice) {
		this.purchasePrice.set(purchasePrice);
	}

	public Money getSalePrice() {
		return salePrice.get();
	}

	public ObjectProperty<Money> salePriceProperty() {
		return salePrice;
	}

	public void setSalePrice(Money salePrice) {
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