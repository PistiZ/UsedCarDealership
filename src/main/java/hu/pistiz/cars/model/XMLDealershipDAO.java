package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;
import hu.pistiz.cars.util.PathUtil;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XMLDealershipDAO implements DealershipDAO {

	Path dealershipDir = PathUtil.getDealershipDir();

	@Override
	public void addDealership(Dealership dealership) {
		try {
			File dealershipFile = new File((Paths.get(dealershipDir.toString(), "dealership.xml")).toUri());
			boolean fileCreated = false;
			try {
				fileCreated = dealershipFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (fileCreated)
				JAXBUtil.toXML(dealership, new FileOutputStream(dealershipFile));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Dealership getDealership() throws FileNotFoundException {
		Dealership dealership = null;
		try {
			dealership = JAXBUtil.fromXML(Dealership.class, new FileInputStream(new File((Paths.get(dealershipDir.toString(), "dealership.xml")).toUri())));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return dealership;
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
