package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;
import hu.pistiz.cars.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * <a href="Dealership.html">Dealership</a> objektumok XML-ben történő kezelését végző osztály.
 */
public class XMLDealershipDAO implements DealershipDAO {

	/**
	 * A kereskedés mappája
	 */
	Path dealershipDir = PathUtil.getDealershipDir();

	/**
	 * A naplózást végző logger.
	 */
	public static Logger logger = LoggerFactory.getLogger(XMLDealershipDAO.class);

	/**
	 * Létrehozza a kereskedés XML fájlját.
	 *
	 * @param dealership a menteni kívánt kereskedés
	 */
	@Override
	public void addDealership(Dealership dealership) {
		try {
			File dealershipFile = new File((Paths.get(dealershipDir.toString(), "dealership.xml")).toUri());
			boolean fileCreated = false;
			try {
				fileCreated = dealershipFile.createNewFile();
			} catch (IOException e) {
				logger.error(dealershipFile.toURI().toString() + " fájl elkészítése sikertelen.", e);
			}

			if (fileCreated) {
				JAXBUtil.toXML(dealership, new FileOutputStream(dealershipFile));
				logger.info(dealership.getName() + " kereskedés felvéve.");
			}
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
	}

	/**
	 * XML-ből betölti a tárolt kereskedést.
	 *
	 * @return a betöltött kereskedés
	 * @throws FileNotFoundException kiváltódik, ha a dealership.xml nem található
	 */
	@Override
	public Dealership getDealership() throws FileNotFoundException {
		Dealership dealership = null;
		try {
			dealership = JAXBUtil.fromXML(Dealership.class, new FileInputStream(new File((Paths.get(dealershipDir.toString(), "dealership.xml")).toUri())));
			logger.info(dealership.getName() + " betöltése XML-ből sikeres.");
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		}
		return dealership;
	}

	/**
	 * Frissíti a kereskedést XML-ben.
	 *
	 * @param dealership a frissítendő kereskedés
	 */
	@Override
	public void updateDealership(Dealership dealership) {
		try {
			File dealershipFile = new File((Paths.get(dealershipDir.toString(), "dealership.xml")).toUri());
			boolean fileCreated = false;
			if (dealershipFile.exists()) {
				dealershipFile.delete();
				try {
					fileCreated = dealershipFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					fileCreated = dealershipFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileCreated) {
				JAXBUtil.toXML(dealership, new FileOutputStream(dealershipFile));
				logger.info(dealership.getName() + " frissítve.");
			}
		} catch (JAXBException e) {
			logger.error("Sikertelen JAXB-művelet", e);
		} catch (FileNotFoundException e) {
			logger.error("A fájl nem található", e);
		}
	}
}
