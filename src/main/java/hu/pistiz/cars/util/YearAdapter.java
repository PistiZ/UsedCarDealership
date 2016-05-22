package hu.pistiz.cars.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Year;

/**
 * {@link Year} objektumok XML-ben való helyes tárolásáért felelős osztály.
 */
public class YearAdapter extends XmlAdapter<String, Year> {

	/**
	 * A paraméter szövegből készít {@link Year} objektumot.
	 *
	 * @param v az évet tartalmazó {@link String}
	 * @return a készített {@link Year} objektum
	 * @throws Exception bármely hiba esetén
	 */
	public Year unmarshal(String v) throws Exception {
		return Year.of(Integer.parseInt(v));
	}

	/**
	 * A paraméterként kapott {@link Year} objektumot adja vissza szövegként.
	 *
	 * @param v a {@link Year} objektum
	 * @return a détumot tartalmazó {@link String}
	 * @throws Exception bármely hiba esetén
	 */
	public String marshal(Year v) throws Exception {
		return v.toString();
	}

}