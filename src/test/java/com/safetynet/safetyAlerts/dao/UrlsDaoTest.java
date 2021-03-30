package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;


@ExtendWith(MockitoExtension.class)
public class UrlsDaoTest {

	@InjectMocks
	UrlsDaoImpl urlsDao;
	
	public List<PersonModel> persons = new ArrayList<>();
	public List<FirestationModel> firestations = new ArrayList<>();
	
	List<String> medications = null;
	List<String> allergies = null;
	Date birthdayChild = null;
	Date birthdayAdult = null;
	
	
	@BeforeEach
    public void setUp() throws ParseException {
		
        firestations.add(new FirestationModel("fire1","address1"));
		firestations.add(new FirestationModel("fire2","address2"));
		firestations.add(new FirestationModel("fire2","address3"));
		firestations.add(new FirestationModel("fire3","address4"));		
		urlsDao.setAllFireStations(firestations);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthdayChild = "01/01/2001";
		String stringBirthdayAdult = "01/01/1970";
		birthdayChild = simpleDateFormat.parse(stringBirthdayChild);
		birthdayAdult = simpleDateFormat.parse(stringBirthdayAdult);
		
		MedicalrecordModel medicalrecordAdult = new MedicalrecordModel("firstName1", "lastName1", birthdayAdult, medications, allergies);
		MedicalrecordModel medicalrecordChild = new MedicalrecordModel("firstName2", "lastName2", birthdayChild, medications, allergies);
		
		PersonModel person1 = new PersonModel("firstName1","LastName1","address1","city",95000," 06 06 06 06", "mail@mail.com",51);
		PersonModel person2 = new PersonModel("firstName2","LastName1","address1","city",95000," 06 06 06 06", "mail@mail.com",10);
		PersonModel person3 = new PersonModel("firstName1","LastName2","address2","city",95000," 06 06 06 06", "mail@mail.com",51);
		PersonModel person4 = new PersonModel("firstName2","LastName2","address2","city",95000," 06 06 06 06", "mail@mail.com",10);
		person1.setFirestation("fire1");
		person1.setMedicalrecord(medicalrecordAdult);
		person2.setFirestation("fire1");
		person2.setMedicalrecord(medicalrecordChild);
		person3.setFirestation("fire2");
		person3.setMedicalrecord(medicalrecordAdult);
		person4.setFirestation("fire2");
		person4.setMedicalrecord(medicalrecordChild);
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		urlsDao.setAllPersons(persons);
	}
	
	
	@Test
	public void PersonsCoveredByStationU1Test() {
		
		assertThat(urlsDao.allPersonCoveredByOneStation("fire1")).isNotNull();
		assertThat(urlsDao.allPersonCoveredByOneStation("fire3")).isNotNull();
		assertThrows(DataNotFoundException.class, () -> urlsDao.allPersonCoveredByOneStation("fireNotExist"));
	}
	
	
	@Test
	public void allChildsByAdressWithParentsTest() {
		
		assertThat(urlsDao.allChildsByAdressWithParents("address1")).isNotNull();
		assertThrows(DataNotFoundException.class, () -> urlsDao.allChildsByAdressWithParents("addressNotExist"));
	}
	

	@Test
	public void PhoneNumbersForStationTest() {
		
		assertThat(urlsDao.phoneNumbersForStation("fire1")).isNotNull();
		assertThrows(DataNotFoundException.class, () -> urlsDao.phoneNumbersForStation("fireNotExist"));
	}
	
	@Test
	public void personsByAdressWithStationTest() {
		
		assertThat(urlsDao.personsByAdressWithStation("address1")).isNotNull();
		assertThrows(DataNotFoundException.class, () -> urlsDao.personsByAdressWithStation("addressNotExist"));
	}
	
	@Test
	public void familystByAdressWithStationTest() {
		
		assertThat(urlsDao.familystByAdressWithStation("fire1")).isNotNull();
		assertThrows(DataNotFoundException.class, () -> urlsDao.familystByAdressWithStation("fireNotExist"));
	}
	
	@Test
	public void infoByPersonTest() {
		
		assertThat(urlsDao.infoByPerson("firstName1","LastName1")).isNotNull();
		assertThrows(DataNotFoundException.class, () -> urlsDao.infoByPerson("firstNameExist","lastNameNotExist"));
	}
	
	@Test
	public void allMailOfCityTest() {
		
		assertThat(urlsDao.allMailOfCity("city")).isNotNull();
		assertThrows(DataNotFoundException.class, () -> urlsDao.allMailOfCity("cityNotExist"));
	}
	
	
}
