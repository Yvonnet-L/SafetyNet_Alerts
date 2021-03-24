package com.safetynet.safetyAlerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetyAlerts.dao.PersonDao;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.PersonService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	PersonDao personDao;

	@Autowired
	PersonService personService;

	@Test
	public void getPersonsTest() throws Exception {
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}

	@Test
	public void getPersonByFirstNameAndLastNameTest() throws Exception {
		mockMvc.perform(get("/person/Boyd"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", is("1509 Culver St")));
		
	}

	@Test
	public void getPersonByIdWithEspaceTest() throws Exception {
		mockMvc.perform(get("/person/  ")).andExpect(status().isBadRequest());
	}

	@Test
	public void addPersonTest() throws Exception {

		PersonModel personToCreate = new PersonModel("firstNameTest1", "lastNameTest1", "address Test", "CityTest", 10000,
				"telTest 02 02 02 02", "test@test.com", 15);

		mockMvc.perform(post("/person/").contentType("application/json").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personToCreate))).andExpect(status().isCreated());
		
		personDao.delete(personToCreate);
	}

	@Test
	public void PutPersonTest() throws Exception {

		PersonModel personUpDate = new PersonModel("firstNameTest", "lastNameTest", "address Test2", "CityTest", 10000,
				"telTest 02 02 02 02", "test@test.com", 15);
		
		personDao.save(personUpDate);
		
		mockMvc.perform(put("/person/").contentType("application/json").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personUpDate))).andExpect(status().isCreated());
		
		personDao.delete(personUpDate);
	}

	@Test
	public void deletePersonTest() throws Exception {		
		
		PersonModel personDelete = new PersonModel("firstNameTest", "lastNameTest", "address Test2", "CityTest", 10000,
				"telTest 02 02 02 02", "test@test.com", 15);
		
		personDao.save(personDelete);

		mockMvc.perform(delete("/person/").contentType("application/json").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personDelete))).andExpect(status().isOk());
	}

}
