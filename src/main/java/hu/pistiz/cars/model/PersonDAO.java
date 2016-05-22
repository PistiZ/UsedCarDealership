// CHECKSTYLE:OFF
package hu.pistiz.cars.model;

import java.io.FileNotFoundException;

/**
 * Személyek kezelésére szolgáló DAO interfész.
 */
public interface PersonDAO {

	void addPerson(Person person);

	Person getPerson() throws FileNotFoundException;

}
