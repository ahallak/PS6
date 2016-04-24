package domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import util.LocalDateAdapter;

public class PersonDomainModel {

	private UUID PersonID;
	private String firstName;
	private String lastName;
	private String street;
	private Integer postalCode;
	private String city;
	private Date birthday;

	/**
	 * Default constructor.
	 */
	
	public PersonDomainModel() {
		this.PersonID = UUID.randomUUID();
	}
	
	public PersonDomainModel(String firstName, String lastName, String street, Integer postalCode,
			String city, Date birthday) {
		super();
		this.PersonID = UUID.randomUUID();		
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.birthday = birthday;
	}
	
	public PersonDomainModel(UUID personID) {
		super();
		this.PersonID = personID;		
	}
	
	public UUID getPersonID() {
		return PersonID;
	}



	protected void setPersonID(UUID personID) {
		PersonID = personID;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public Integer getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	
}