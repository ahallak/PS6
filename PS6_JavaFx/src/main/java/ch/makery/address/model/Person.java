package ch.makery.address.model;

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

import ch.makery.address.util.LocalDateAdapter;
import domain.PersonDomainModel;

public class Person extends PersonDomainModel {

    public Person() {
    
    }

    public Person(UUID PersonID, String firstName, String lastName, String street, int postalCode, String city, Object birthday ) {
    	this.setPersonID(PersonID);
        this.setFirstName(firstName);
        this.setLastName(lastName);

        this.setStreet(street);
        this.setPostalCode(postalCode);
        this.setCity(city);
        this.setBirthday((Date)birthday);
    }
    
    
    public StringProperty getFirstNameProperty()
    {
    	return new SimpleStringProperty(getFirstName());    	
    }
    
    public StringProperty getLastNameProperty()
    {
    	return new SimpleStringProperty(getLastName());    	
    }
    
    public StringProperty getStreetProperty()
    {
    	return new SimpleStringProperty(getStreet());    	
    }
    
    public StringProperty getCityProperty()
    {
    	return new SimpleStringProperty(getCity());    	
    }
    
    public IntegerProperty getPostalCodeProperty()
    {
    	return new SimpleIntegerProperty(getPostalCode());    	
    }
        
}