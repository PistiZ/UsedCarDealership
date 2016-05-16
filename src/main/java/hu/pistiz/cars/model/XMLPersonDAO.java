package hu.pistiz.cars.model;

import hu.pistiz.cars.util.JAXBUtil;
import hu.pistiz.cars.util.PathUtil;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XMLPersonDAO implements PersonDAO {

	Path personDir = PathUtil.getPersonDir();

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

			if (fileCreated)
				JAXBUtil.toXML(person, new FileOutputStream(personFile));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person getPerson() throws FileNotFoundException {
		Person person = null;
		try {
			person = JAXBUtil.fromXML(Person.class, new FileInputStream(new File((Paths.get(personDir.toString(), "owner.xml")).toUri())));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return person;
	}
}
