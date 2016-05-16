package hu.pistiz.cars.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Year;

public class YearAdapter extends XmlAdapter<String, Year> {

	public Year unmarshal(String v) throws Exception {
		return Year.of(Integer.parseInt(v));
	}

	public String marshal(Year v) throws Exception {
		return v.toString();
	}

}