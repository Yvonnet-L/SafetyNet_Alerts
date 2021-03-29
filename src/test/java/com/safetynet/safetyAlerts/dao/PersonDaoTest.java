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

import com.safetynet.safetyAlerts.exceptions.DataExistException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.StringUtilsService;

@ExtendWith(MockitoExtension.class)
public class PersonDaoTest {

	@InjectMocks
	PersonDaoImpl personDao;
	
	StringUtilsService stringUtilsService = new StringUtilsService();

	public List<PersonModel> persons = new ArrayList<>();
	public List<FirestationModel> firestations = new ArrayList<>();
	public List<MedicalrecordModel> medicalrecords = new ArrayList<>();
	
	PersonModel personPost;
	PersonModel personPut;
	PersonModel personDelete;

	@BeforeEach
	private void init() throws ParseException {

		PersonModel person1 = new PersonModel("firstName1", "lastName1", "address1", "CityTest", 10000, "telTest", "test@test.com", 15);
		PersonModel person2 = new PersonModel("firstName2", "lastName1", "address1", "CityTest", 10000, "telTest", "test@test.com", 15);
		PersonModel person3 = new PersonModel("firstName3", "lastName2", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15);
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		personDao.setAllPersons(persons);
		
		FirestationModel firestation1 = new FirestationModel("fire1","address1");
    	FirestationModel firestation2 = new FirestationModel("fire2","address2");
        firestations.add(firestation1);
		firestations.add(firestation2);	
		personDao.setAllFireStations(firestations);
		
		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);
		MedicalrecordModel medicalrecord1 = new MedicalrecordModel("firstName1", "lastName1", birthday, medications, allergies);
		MedicalrecordModel medicalrecord2 = new MedicalrecordModel("firstName2", "lastName1", birthday, medications, allergies);
		MedicalrecordModel medicalrecord3 = new MedicalrecordModel("firstName3", "lastName2", birthday, medications, allergies);
		medicalrecords.add(medicalrecord1);
		medicalrecords.add(medicalrecord2);
		medicalrecords.add(medicalrecord3);		
		personDao.setAllMedicalrecords(medicalrecords);		
	}

	
	@Test
	public void findAllPersonTest() throws Exception {
		assertThat(personDao.findAll().size()).isEqualTo(3);
	}

	@Test
	public void findPersonByIdTest() throws Exception {
		assertThat(personDao.findById("lastName1").size()).isEqualTo(2);
		assertThrows(DataNotFoundException.class, () -> personDao.findById("lastNameNotexist"));
		
	}

	@Test
	public void postPersonTest() throws Exception {
		
		int size = personDao.findAll().size()+1;
		personPost = new PersonModel("firstName1", "lastName2", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15);
		
		assertThat(personDao.save(personPost).getCity()).isNotEmpty();
		assertThat(personDao.findAll().size()).isEqualTo(size);		
		assertThrows(DataExistException.class, () -> personDao.save(personPost));

	}

	@Test
	public void putPersonTest() throws Exception {
		
		personPut = new PersonModel("firstName1", "lastName1", "addressTest", "CityTest2", 10000, "telTest", "test@test.com", 15);
		PersonModel personNotExist = new PersonModel("firstNameNotExist", "lastNameNotExist", "addressTest", "CityTest2", 10000, "telTest", "test@test.com", 15);
		
		assertThat(personDao.put(personPut).getCity()).isEqualTo("CityTest2");	
		assertThrows(DataNotFoundException.class, () -> personDao.put(personNotExist));

	}

	@Test
	public void deletePersonTest() throws Exception {
		
		int size = personDao.findAll().size()-1;
		personDelete = new PersonModel("firstName3", "lastName2", "addressTest", "CityTest2", 10000, "telTest", "test@test.com", 15);
		
		assertThat(personDao.delete(personDelete)).isNotEmpty();
		assertThat(personDao.findAll().size()).isEqualTo(size);	
		assertThrows(DataNotFoundException.class, () -> personDao.delete(personDelete));
	}

}
