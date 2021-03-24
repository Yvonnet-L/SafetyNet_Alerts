package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.model.PersonModel;

@SpringBootTest
public class PersonDaoTest {

	@Autowired
	PersonDao personDao;
	
	PersonModel personPost;
	PersonModel personPut;
	PersonModel personDelete;

	@BeforeEach
	private void init() {

		PersonModel person1 = new PersonModel("firstName1", "lastName1", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15);
		PersonModel person2 = new PersonModel("firstName2", "lastName1", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15);
		PersonModel person3 = new PersonModel("firstName3", "lastName2", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15);

		List<PersonModel> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);
		personList.add(person3);

		personDao.setAllPersons(personList);
		
		personPost = new PersonModel("firstName1", "lastName2", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15);
		personPut = new PersonModel("firstName1", "lastName1", "addressTest", "CityTest2", 10000, "telTest", "test@test.com", 15);
		personDelete = new PersonModel("firstName3", "lastName2", "addressTest", "CityTest2", 10000, "telTest", "test@test.com", 15);
	}

	
	@Test
	public void findAllPersonTest() throws Exception {

		assertThat(personDao.findAll().size()).isEqualTo(3);
	}

	@Test
	public void findPersonByIdTest() throws Exception {
		assertThat(personDao.findById("lastName1").size()).isEqualTo(2);
	}

	@Test
	public void postPersonTest() throws Exception {
		assertThat(personDao.save(personPost).getCity()).isNotEmpty();
	}

	@Test
	public void putPersonTest() throws Exception {
		assertThat(personDao.put(personPut).getCity()).isEqualTo("CityTest2");

	}

	@Test
	public void deletePersonTest() throws Exception {
		assertThat(personDao.delete(personDelete));
	}

}
