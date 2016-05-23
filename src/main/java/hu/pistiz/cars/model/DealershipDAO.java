// CHECKSTYLE:OFF
package hu.pistiz.cars.model;

import java.io.FileNotFoundException;

/**
 * Kereskedés kezelésére szolgáló DAO interfész.
 */
public interface DealershipDAO {

	void addDealership(Dealership dealership);

	Dealership getDealership() throws FileNotFoundException;

	void updateDealership(Dealership dealership);

}
