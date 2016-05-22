package hu.pistiz.cars.util;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Objektumok JAXB-vel való kimentésére és visszatöltésére szolgáló osztály.
 */
public class JAXBUtil {

	/**
	 * Objektumot XML-be szerializál. A kimeneti dokumentum UTF-8 karakterkódolású.
	 *
	 * @param o a szerializálandó objektum
	 * @param os az írandó {@link OutputStream}
	 * @throws JAXBException bármely JAXB-hiba esetén váltódik ki
	 */
	public static void toXML(Object o, OutputStream os) throws JAXBException {
		JAXBContext	context = JAXBContext.newInstance(o.getClass());
		Marshaller	marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(o, os);
	}

	/**
	 *
	 * @param clazz az objektum osztálya
	 * @param is az olvasandó {@link InputStream}
	 * @param <T> generikus típus
	 * @return az eredmény-objektum
	 * @throws JAXBException bármely JAXB-hiba esetén váltódik ki
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXML(Class<T> clazz, InputStream is) throws JAXBException {
		JAXBContext	context = JAXBContext.newInstance(clazz);
		Unmarshaller	unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(is);
	}

}
