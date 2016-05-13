package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CarDAOImpl implements CarDAO {

	@Override
	public String priceToString(long price) {
		return Long.toString(price) + " Ft";
	}

	@Override
	public String kmToString(long km) {
		return Long.toString(km) + " km";
	}

	@Override
	public void carToXML(Car car, String fileName) {
		Path dir = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles");
		Path path = Paths.get(System.getProperty("user.home"), ".UserCarDealershipFiles", fileName);
		File saveFile = path.toFile();

		if (!new File(dir.toUri()).exists()) {
			try {
				Files.createDirectory(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			OutputStream os = new FileOutputStream(saveFile);
			JAXBUtil.toXML(car, os);
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}