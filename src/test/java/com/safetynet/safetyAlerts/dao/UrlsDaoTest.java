package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
		
		PersonModel person1 = new PersonModel("firstName1","LastName1","address1","city",95000," 06 06 06 06", "mail@mail1.com",51);
		PersonModel person2 = new PersonModel("firstName2","LastName1","address1","city",95000," 06 06 06 06", "mail@mail1.com",10);
		PersonModel person3 = new PersonModel("firstName1","LastName2","address2","city",95000," 06 06 06 06", "mail@mail2.com",51);
		PersonModel person4 = new PersonModel("firstName2","LastName2","address2","city",95000," 06 06 06 06", "mail@mail3.com",10);
		PersonModel person5 = new PersonModel("firstName2","LastName2","address2","city",95000," 06 06 06 06", "mail@mail4.com",10);
		person1.setFirestation("fire1");
		person1.setMedicalrecord(medicalrecordAdult);
		person2.setFirestation("fire1");
		person2.setMedicalrecord(medicalrecordChild);
		person3.setFirestation("fire2");
		person3.setMedicalrecord(medicalrecordAdult);
		person4.setFirestation("fire2");
		person4.setMedicalrecord(medicalrecordChild);
		person5.setFirestation("fire2");
		person5.setMedicalrecord(medicalrecordChild);
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		persons.add(person5);
		urlsDao.setAllPersons(persons);
	}
	
	
	@Test
	@DisplayName("Tests U1 - Liste des personnes couverte par une station")
	public void PersonsCoveredByStationU1Test() {
		
		assertThat(urlsDao.allPersonCoveredByOneStation("fire1").getNbAdult()).isEqualTo(1);
		assertThat(urlsDao.allPersonCoveredByOneStation("fire1").getNbChild()).isEqualTo(1);
		assertThat(urlsDao.allPersonCoveredByOneStation("fire2").getNbChild()).isEqualTo(2);
		assertThat(urlsDao.allPersonCoveredByOneStation("fire3").getNbAdult()).isEqualTo(0);
		assertThrows(DataNotFoundException.class, () -> urlsDao.allPersonCoveredByOneStation("fireNotExist"));
	}
	
	
	@Test
	@DisplayName("Tests U2 - Liste des enfants par adresse")
	public void allChildsByAdressWithParentsTest() {
		
		assertThat(urlsDao.allChildsByAdressWithParents("address1").getEnfants().size()).isEqualTo(1);
		assertThat(urlsDao.allChildsByAdressWithParents("address1").getParents().size()).isEqualTo(1);
		assertThat(urlsDao.allChildsByAdressWithParents("address2").getEnfants().size()).isEqualTo(2);
		assertThat(urlsDao.allChildsByAdressWithParents("address2").getParents().size()).isEqualTo(1);
		assertThrows(DataNotFoundException.class, () -> urlsDao.allChildsByAdressWithParents("addressNotExist"));
	}
	

	@Test
	@DisplayName("Tests U3 - Liste des numéros de téléphone pour une station")
	public void PhoneNumbersForStationTest() {
		
		assertThat(urlsDao.phoneNumbersForStation("fire1").getPhoneList().size()).isEqualTo(2);
		assertThat(urlsDao.phoneNumbersForStation("fire2").getPhoneList().size()).isEqualTo(3);
		assertThat(urlsDao.phoneNumbersForStation("fire3").getPhoneList().size()).isEqualTo(0);
		assertThrows(DataNotFoundException.class, () -> urlsDao.phoneNumbersForStation("fireNotExist"));
	}
	
	@Test
	@DisplayName("Tests U4 - Liste des personnes par adresses d'une station")
	public void personsByAdressWithStationTest() {
		
		assertThat(urlsDao.personsByAdressWithStation("address1").getFireStation()).isEqualTo("fire1");
		assertThat(urlsDao.personsByAdressWithStation("address2").getFireStation()).isEqualTo("fire2");
		assertThat(urlsDao.personsByAdressWithStation("address2").getPersons().size()).isEqualTo(3);
		assertThat(urlsDao.personsByAdressWithStation("address1").getPersons().size()).isEqualTo(2);
		assertThrows(DataNotFoundException.class, () -> urlsDao.personsByAdressWithStation("addressNotExist"));
	}
	
	@Test
	@DisplayName("Tests U5 - Liste des familles par adresse pour une station")
	public void familystByAdressWithStationTest() {
		
		assertThat(urlsDao.familystByAdressWithStation("fire1").getPersonsListU5().size()).isEqualTo(1);
		assertThat(urlsDao.familystByAdressWithStation("fire2").getPersonsListU5().size()).isEqualTo(1);		
		assertThrows(DataNotFoundException.class, () -> urlsDao.familystByAdressWithStation("fireNotExist"));
	}
	
	@Test
	@DisplayName("Tests U6 - Liste des personnes pour un prénom et nom")
	public void infoByPersonTest() {
		
		assertThat(urlsDao.infoByPerson("firstName1","LastName1").getPersons().size()).isEqualTo(1);
		assertThrows(DataNotFoundException.class, () -> urlsDao.infoByPerson("firstNameExist","lastNameNotExist"));
	}
	
	@Test
	@DisplayName("Tests U7 - Liste des mails pour une ville")
	public void allMailOfCityTest() {
		
		assertThat(urlsDao.allMailOfCity("city").getMails().size()).isEqualTo(4);
		assertThrows(DataNotFoundException.class, () -> urlsDao.allMailOfCity("cityNotExist"));
	}
	
	
}
