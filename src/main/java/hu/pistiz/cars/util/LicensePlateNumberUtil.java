package hu.pistiz.cars.util;

public class LicensePlateNumberUtil {

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
