package com.safetynet.safetyAlerts.dao;


	
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.model.PersonModel;

	@SpringBootTest
	@AutoConfigureMockMvc
	public class PersonDaoTest {

		@Autowired
		PersonDao personDao;

		@Test
		public void findAllPersonTest() throws Exception {
			Thread.sleep(1000);
			assertThat(personDao.findAll().size()).isEqualTo(24);
		}
		
		@Test
		public void findPersonByIdTest() throws Exception {
			assertThat(personDao.findById("Boyd").size()).isEqualTo(6);
		} 
		
		@Test
		public void postPersonTest() throws Exception {
			PersonModel personPost = new PersonModel("firstNameTest", "lastNameTest", "address Test", "CityTest", 10000,
					"telTest 02 02 02 02","test@test.com",15);;
			assertThat(personDao.save(personPost));	
		}
		

		@Test
		public void postPersonWithSpaceTest() throws Exception {		
			PersonModel personPost = new PersonModel(" ", " ", "address Test", "CityTest", 10000,
					"telTest 02 02 02 02","test@test.com",15);
			assertThat(personDao.save(personPost));	
		}
		
		@Test
		public void putPersonTest() throws Exception {
			PersonModel personPut = new PersonModel("firstNameTest", "lastNameTest", "address Test2", "CityTest", 10000,
					"telTest 02 02 02 02","test@test.com",15);;
			assertThat(personDao.put(personPut));	
		}
		
		@Test
		public void deletePersonTest() throws Exception {
			PersonModel personDelete = new PersonModel("firstNameTest", "lastNameTest", "address Test2", "CityTest", 10000,
					"telTest 02 02 02 02","test@test.com",15);;
			assertThat(personDao.delete(personDelete));
		}

		
		@Test
		public void deletePersonWithJsonNullErrorTest() throws Exception {
			PersonModel personDelete = new PersonModel(" ", " ", "address Test2", "CityTest", 10000,
					"telTest 02 02 02 02","test@test.com",15);
			assertThat(personDao.delete(personDelete));
		}
}
