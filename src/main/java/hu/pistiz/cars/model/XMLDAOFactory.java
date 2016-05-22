// CHECKSTYLE:OFF
package hu.pistiz.cars.model;

public class XMLDAOFactory {

	public static CarDAO getCarDAO() {
		return new XMLCarDAO();
	}

	public static DealershipDAO getDealershipDAO() {
		return new XMLDealershipDAO();
	}

	public static PersonDAO getPersonDAO() {
		return new XMLPersonDAO();
	}

}