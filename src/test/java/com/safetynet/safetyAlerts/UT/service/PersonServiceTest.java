package com.safetynet.safetyAlerts.UT.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetyAlerts.dao.PersonDaoImpl;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@InjectMocks
	PersonService personService;
	
	@Mock
	PersonDaoImpl personDao;
	
	public List<PersonModel> persons = new ArrayList<>();

	PersonModel personNameNotConform1 = new PersonModel(" ", " ", "address Test", "CityTest", 10000, "telTest 02 02 02 02","test@test.com",15);
	PersonModel personNameNotConform2 = new PersonModel("firtsName", "***", "address Test", "CityTest", 10000, "telTest 02 02 02 02","test@test.com",15);
	PersonModel personNameNotConform3 = new PersonModel("####", "lastName", "address Test", "CityTest", 10000, "telTest 02 02 02 02","test@test.com",15);
	
	PersonModel person = new PersonModel("firstName", "lastName", "address1", "CityTest", 10000, "telTest", "test@test.com", 15);


	@Test
	public void findAllPersonTest() throws Exception {
		// GIVEN
		persons.add(new PersonModel("firstName1", "lastName", "address1", "CityTest", 10000, "telTest", "test@test.com", 15));
		persons.add(new PersonModel("firstName3", "lastName2", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15));
		persons.add(new PersonModel("firstName2", "lastName", "address1", "CityTest", 10000, "telTest", "test@test.com", 15));
		// WHEN
		Mockito.when(personDao.findAll()).thenReturn(persons);
		// THEN
		assertThat(personService.findAll().size()).isEqualTo(3);
	}
	
	@Test
	public void findPersonByIdTest() throws Exception {
		// GIVEN
		persons.add(new PersonModel("firstName1", "lastName", "address1", "CityTest", 10000, "telTest", "test@test.com", 15));
		persons.add(new PersonModel("firstName2", "lastName", "address1", "CityTest", 10000, "telTest", "test@test.com", 15));
		// WHEN
		Mockito.when(personDao.findById("lastName")).thenReturn(persons);
		// THEN
		assertThat(personService.findById("lastName").size()).isEqualTo(2);
	} 
	
	@Test
	public void addPersonTest() throws Exception {
		// GIVEN
		// WHEN
		Mockito.when(personDao.save(person)).thenReturn(person);
		// THEN
		assertThat(personService.addPerson(person).equals(person));	
	}
	

	@Test
	public void addPersonWithNameNotConformTest() throws Exception {				
		assertThrows(DataNotConformException.class, () -> personService.addPerson(personNameNotConform1));
		assertThrows(DataNotConformException.class, () -> personService.addPerson(personNameNotConform2));
		assertThrows(DataNotConformException.class, () -> personService.addPerson(personNameNotConform3));
	}
	
	
	@Test
	public void putPersonTest() throws Exception {
		// GIVEN
		// WHEN
		Mockito.when(personDao.put(person)).thenReturn(person);
		// THEN
		assertThat(personService.put(person).equals(person));	
	}
	
	@Test
	public void putPersonWithNameNotConformTest() throws Exception {				
		assertThrows(DataNotConformException.class, () -> personService.put(personNameNotConform1));
		assertThrows(DataNotConformException.class, () -> personService.put(personNameNotConform2));
		assertThrows(DataNotConformException.class, () -> personService.put(personNameNotConform3));
	}
		
	@Test
	public void deletePersonTest() throws Exception {
		// WHEN
		Mockito.when(personDao.delete(person)).thenReturn(persons);
		// THEN
		assertThat(personService.delete(person).equals(persons));	
	}
	
	@Test
	public void deletePersonWithJsonNotConformTest() throws Exception {
		assertThrows(DataNotConformException.class, () -> personService.delete(personNameNotConform1));
		assertThrows(DataNotConformException.class, () -> personService.delete(personNameNotConform2));
		assertThrows(DataNotConformException.class, () -> personService.delete(personNameNotConform3));
	}
	
}
