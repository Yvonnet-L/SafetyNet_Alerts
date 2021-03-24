package com.safetynet.safetyAlerts.service;


	import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.PersonModel;

		@SpringBootTest
		@AutoConfigureMockMvc
		public class PersonServiceTest {

			@Autowired
			PersonService personService;

			@Test
			public void findAllPersonTest() throws Exception {
				Thread.sleep(1000);
				//25
				assertThat(personService.findAll().size()).isEqualTo(23);
			}
			
			@Test
			public void findPersonByIdTest() throws Exception {
				assertThat(personService.findById("Boyd").size()).isEqualTo(6);
			} 
			
			@Test
			public void postPersonTest() throws Exception {
				PersonModel personPost = new PersonModel("firstNameTest", "lastNameTest", "address Test", "CityTest", 10000,
						"telTest 02 02 02 02","test@test.com",15);;
				assertThat(personService.addPerson(personPost));	
			}
			

			@Test
			public void postPersonWithSpaceTest() throws Exception {		
				PersonModel personPost = new PersonModel(" ", " ", "address Test", "CityTest", 10000,
						"telTest 02 02 02 02","test@test.com",15);
				assertThrows(DataNotConformException.class, () -> personService.addPerson(personPost));
			}
			
			@Test
			public void putPersonTestNotExist() throws Exception {
				PersonModel personPut = new PersonModel("firstNameTest", "lastNameTest", "address Test2", "CityTest", 10000,
						"telTest 02 02 02 02","test@test.com",15);
				assertThrows(DataNotFoundException.class, () -> personService.put(personPut));
			}
			
			@Test
			public void deletePersonTest() throws Exception {
				PersonModel personDelete = new PersonModel("firstNameTest", "lastNameTest", "address Test2", "CityTest", 10000,
						"telTest 02 02 02 02","test@test.com",15);;
				assertThat(personService.delete(personDelete));
			}

			
			@Test
			public void deletePersonWithJsonNotConformTest() throws Exception {
				PersonModel personDelete = new PersonModel(" ", " ", "address Test2", "CityTest", 10000,
						"telTest 02 02 02 02","test@test.com",15);
				assertThrows(DataNotConformException.class, () -> personService.delete(personDelete));
			}
	
}
