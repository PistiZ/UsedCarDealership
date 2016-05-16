package hu.pistiz.cars.model;

import java.io.FileNotFoundException;

public interface PersonDAO {

	void addPerson(Person person);

	Person getPerson() throws FileNotFoundException;

}
