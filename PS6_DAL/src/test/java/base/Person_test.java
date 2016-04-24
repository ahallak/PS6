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

	private static PersonDomainModel per1;
	private static PersonDomainModel per2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Date bDate = null;
		try {
			bDate = new SimpleDateFormat("yyyy-MM-dd").parse("1997-04-09");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		per1 = new PersonDomainModel();
		per1.setFirstName("Amjed");
		per1.setLastName("Hallak");
		per1.setBirthday(bDate);
		per1.setCity("Hockessin");
		per1.setPostalCode(19707);
		per1.setStreet("88 Chandler");

		Date bDate2 = null;
		try {
			bDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("1997-04-09");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		per2 = new PersonDomainModel();
		per2.setFirstName("FirstName");
		per2.setLastName("LastName");
		per2.setBirthday(bDate2);
		per2.setCity("PersonCity");
		per2.setPostalCode(19707);
		per2.setStreet("PersonStreett");

	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
			PersonDAL.deletePerson(per1.getPersonID());
			per = PersonDAL.getPerson(per1.getPersonID());
			assertNull("Person isn't in db anymore", per);
		} catch (IllegalArgumentException e) {
			assertNull("No one present in db", per);
		}
	}

	@Test
	public void addPersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("No one present in db", per);
		PersonDAL.addPerson(per1);

		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("Person added to db", per);
	}

	@Test
	public void updatePersonTest() {
		PersonDomainModel per;
		final String newLastName = "Last";
		final String oldLastName = per1.getLastName();

		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("No one present ind db", per);
		PersonDAL.addPerson(per1);

		per1.setLastName(newLastName);
		PersonDAL.updatePerson(per1);

		per = PersonDAL.getPerson(per1.getPersonID());

		assertTrue("Last name has changed", per1.getLastName() == newLastName);
		assertFalse("Last name has changed", per1.getLastName() == oldLastName);
	}

	@Test
	public void deletePersonTest() {
		PersonDomainModel per;
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("No one present in db", per);

		PersonDAL.addPerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("Person added to db", per);

		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("No one present in db", per);

	}

	@Test
	public void getPersonsTest() {
		PersonDomainModel per;
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("No one present in db", per);
		PersonDAL.addPerson(per1);

		per = PersonDAL.getPerson(per1.getPersonID());
		System.out.println(per1.getPersonID() + " found");
		assertNotNull("Person added to db", per);
		PersonDAL.addPerson(per2);

		per = PersonDAL.getPerson(per2.getPersonID());
		System.out.println(per1.getPersonID() + " also found");
		assertNotNull("Person added to db", per);

		pers = PersonDAL.getPersons();
		assertTrue("People in db", pers.size() >= 1);

	}

}