package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_test {

	private static PersonDomainModel p1;
	private static PersonDomainModel p2;

	@BeforeClass
	
	//allows date creation
	public static void setUpBeforeClass() throws Exception {

		Date bDate = null;
		try {
			bDate = new SimpleDateFormat("yyyy-MM-dd").parse("1997-04-09");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//creating new model of a person
		p1 = new PersonDomainModel();
		p1.setFirstName("Amjed");
		p1.setLastName("Hallak");
		p1.setBirthday(bDate);
		p1.setCity("Hockessin");
		p1.setPostalCode(19707);
		p1.setStreet("88 Chandler");

		Date bDate2 = null;
		try {
			bDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("1997-04-09");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//second test person
		p2 = new PersonDomainModel();
		p2.setFirstName("FirstName");
		p2.setLastName("LastName");
		p2.setBirthday(bDate2);
		p2.setCity("PersonCity");
		p2.setPostalCode(19707);
		p2.setStreet("PersonStreett");

	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//removing from database
		PersonDomainModel per = null;
		ArrayList<PersonDomainModel> pers = PersonDAL.getPersons();
		if (pers.size() >= 1) {
			for (PersonDomainModel p : pers) {

				PersonDAL.deletePerson(p.getPersonID());
				per = PersonDAL.getPerson(p.getPersonID());
				assertNull("Person isn't in db anymore", per);

			}
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per = null;
		try {
			//attempt of removing from db
			PersonDAL.deletePerson(p1.getPersonID());
			per = PersonDAL.getPerson(p1.getPersonID());
			assertNull("Person isn't in db anymore", per);
		} catch (IllegalArgumentException e) {
			assertNull("No one present in db", per);
		}
	}

	@Test
	public void addPersonTest() {
		//test adding a person
		PersonDomainModel per;
		per = PersonDAL.getPerson(p1.getPersonID());
		assertNull("No one present in db", per);
		PersonDAL.addPerson(p1);

		per = PersonDAL.getPerson(p1.getPersonID());
		System.out.println(p1.getPersonID() + " found");
		assertNotNull("Person added to db", per);
	}

	@Test
	public void updatePersonTest() {
		//test changing person attributes
		PersonDomainModel per;
		final String newLastName = "Last";
		final String oldLastName = p1.getLastName();

		per = PersonDAL.getPerson(p1.getPersonID());
		assertNull("No one present ind db", per);
		PersonDAL.addPerson(p1);

		p1.setLastName(newLastName);
		PersonDAL.updatePerson(p1);

		per = PersonDAL.getPerson(p1.getPersonID());

		assertTrue("Last name has changed", p1.getLastName() == newLastName);
		assertFalse("Last name has changed", p1.getLastName() == oldLastName);
	}

	@Test
	public void deletePersonTest() {
		//test for removing people from database
		PersonDomainModel per;
		per = PersonDAL.getPerson(p1.getPersonID());
		assertNull("No one present in db", per);

		PersonDAL.addPerson(p1);
		per = PersonDAL.getPerson(p1.getPersonID());
		System.out.println(p1.getPersonID() + " found");
		assertNotNull("Person added to db", per);

		PersonDAL.deletePerson(p1.getPersonID());
		per = PersonDAL.getPerson(p1.getPersonID());
		assertNull("No one present in db", per);

	}

	@Test
	public void getPersonsTest() {
		//get person information
		PersonDomainModel per;
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		per = PersonDAL.getPerson(p1.getPersonID());
		assertNull("No one present in db", per);
		PersonDAL.addPerson(p1);

		per = PersonDAL.getPerson(p1.getPersonID());
		System.out.println(p1.getPersonID() + " found");
		assertNotNull("Person added to db", per);
		PersonDAL.addPerson(p2);

		per = PersonDAL.getPerson(p2.getPersonID());
		System.out.println(p1.getPersonID() + " also found");
		assertNotNull("Person added to db", per);

		pers = PersonDAL.getPersons();
		assertTrue("People in db", pers.size() >= 1);

	}

}