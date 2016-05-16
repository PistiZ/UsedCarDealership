@XmlJavaTypeAdapters({
		@XmlJavaTypeAdapter(type = Year.class, value = YearAdapter.class),
		@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
})
package hu.pistiz.cars.util;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDate;
import java.time.Year;