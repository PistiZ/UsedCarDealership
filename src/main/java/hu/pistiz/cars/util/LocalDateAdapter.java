package hu.pistiz.cars.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * {@link LocalDate} objektumok XML-ben való helyes tárolásáért felelős osztály.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	/**
	 * A paraméter szövegből készít {@link LocalDate} objektumot.
	 *
	 * @param v a dátumot tartalmazó {@link String}
	 * @return a készített {@link LocalDate} objektum
	 * @throws Exception bármely hiba esetén
	 */
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	/**
	 * A paraméterként kapott {@link LocalDate} objektumot adja vissza szövegként.
	 *
	 * @param v a {@link LocalDate} objektum
	 * @return a détumot tartalmazó {@link String}
	 * @throws Exception bármely hiba esetén
	 */
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

}