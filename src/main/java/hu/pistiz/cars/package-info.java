/**
 * A projekt fő csomagja.
 */

@XmlJavaTypeAdapters({
		@XmlJavaTypeAdapter(type = Year.class, value = YearAdapter.class),
		@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
})
package hu.pistiz.cars;

import hu.pistiz.cars.util.LocalDateAdapter;
import hu.pistiz.cars.util.YearAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDate;
import java.time.Year;