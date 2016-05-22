package hu.pistiz.cars.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Személyt reprezentáló osztály, példánya a kereskedés tulajdonosaként jelenik meg.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Person {

	@XmlElement
	private String lastName;
	@XmlElement
	private String firstName;
	@XmlElement
	private LocalDate dateOfBirth;

	/**
	 * Paraméter nélküli konstruktor, mely a nevet és a születési dátumot <code>null</code>-ra állítja.
	 */
	public Person() {
		this(null, null, null);
	}

	/**
	 * A <code>Person</code> osztály fő konstruktora.
	 *
	 * @param firstName a létrehozni kívánt személy keresztneve
	 * @param lastName a létrehozni kívánt személy vezetékneve
	 * @param dateOfBirth a létrehozni kívánt személy születési dátuma
	 */
	public Person(String firstName, String lastName, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Visszadja a személy keresztnevét
	 *
	 * @return a személy keresztneve
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Beállítja a személy keresztnevét
	 *
	 * @param firstName a személy keresztneve
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Visszadja a személy vezetéknevét
	 *
	 * @return a személy vezetékneve
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Beállítja a személy vezetéknevét
	 *
	 * @param lastName a személy vezetékneve
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Visszadja a személy születési dátumát
	 *
	 * @return a személy születési dátuma
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Beállítja a személy születési dátumát
	 *
	 * @param dateOfBirth a személy születési dátuma
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}