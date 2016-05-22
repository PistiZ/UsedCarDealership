package hu.pistiz.cars.util;

/**
 * Autók rendszámának érvényességéről döntő osztály.
 */
public class LicensePlateNumberUtil {

	/**
	 * Eldönti, hogy a paraméterként kapott rendszám érvényes-e (3 szám, kötőjel, 3 betű).
	 *
	 * @param licensePlateNumber a vizsgálandó rendszám
	 * @return igaz, ha a rendszám érvényes, ellenben hamis
	 */
	public static boolean isValid(String licensePlateNumber) {
		if (!(licensePlateNumber.length() == 7))
			return false;

		for (int i = 0; i < 3; i++) {
			if (!Character.isAlphabetic(licensePlateNumber.charAt(i)))
				return false;
		}

		if (!(licensePlateNumber.charAt(3) == '-'))
			return false;

		for (int i = 4; i < 6; i++) {
			if (!Character.isDigit(licensePlateNumber.charAt(i)))
				return false;
		}

		return true;
	}

}
