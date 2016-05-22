package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;
import hu.pistiz.cars.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <a href="Person.html">Person</a> objektumok XML-ben történő kezelését végző osztály.
 */
public class XMLPersonDAO implements PersonDAO {

	/**
	 * A személyek mappája.
	 */
	Path personDir = PathUtil.getPersonDir();

	/**
	 * A naplózást végző logger.
	 */
	public static Logger logger = LoggerFactory.getLogger(XMLPersonDAO.class);

	/**
	 * A paraméterként kapott személyt XML-ben tárolja.
	 *
	 * @param person a menteni kívánt személy
	 */
	@Override
	public void addPerson(Person person) {
		try {
			File personFile = new File((Paths.get(personDir.toString(), "owner.xml")).toUri());
			boolean fileCreated = false;
			try {
				fileCreated = personFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (fileCreated) {
				JAXBUtil.toXML(person, new FileOutputStream(personFile));
				logger.info(person.getLastName() + " " + person.getFirstName() + " tulajdonos felvéve.");
			}
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
	}

	/**
	 * Betölti a tulajdonost XML-ből.
	 *
	 * @return a betöltött személy (tulajdonos)
	 * @throws FileNotFoundException kiváltódik, ha az owner.xml nem létezik
	 */
	@Override
	public Person getPerson() throws FileNotFoundException {
		Person person = null;
		try {
			person = JAXBUtil.fromXML(Person.class, new FileInputStream(new File((Paths.get(personDir.toString(), "owner.xml")).toUri())));
			logger.info(person.getLastName() + " " + person.getFirstName() + " tulajdonos betöltve XML-ből");
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		}
		return person;
	}
}
