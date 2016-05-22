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

public class XMLDealershipDAO implements DealershipDAO {

	Path dealershipDir = PathUtil.getDealershipDir();

	public static Logger logger = LoggerFactory.getLogger(XMLDealershipDAO.class);

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

	/*@Override
	public Dealership getDealershipByName(String name) throws FileNotFoundException {
		Dealership dealership = null;
		try {
			 dealership = JAXBUtil.fromXML(Dealership.class, new FileInputStream(new File(Paths.get(dealershipDir.toString(), name) + ".xml")));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return dealership;
	}*/

	@Override
	public List<Car> getAllCars() {
		return null;
	}

	@Override
	public void addCarForSale(Car car) {

	}

	@Override
	public void addSoldCar(Car car) {

	}

	@Override
	public void removeCarForSale(Car car) {

	}

	@Override
	public void removeSoldCar(Car car) {

	}
}
