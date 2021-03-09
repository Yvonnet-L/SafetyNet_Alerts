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
import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.service.FirestationService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirestationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	FirestationDao firestationDao;

	@Autowired
	FirestationService firestationService;

	@Test
	public void getFirestationsTest() throws Exception {
		
		mockMvc.perform(get("/firestations")).andExpect(status().isOk());	
	}

	@Test
	public void getFirestationByIdTest() throws Exception {

		mockMvc.perform(get("/firestation/2"))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].station", is("2")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].address", is("29 15th St")));
	}

	@Test
	public void getFirestationByIdWithEspaceTest() throws Exception {

		mockMvc.perform(get("/firestation/  ")).andExpect(status().isOk());
	}
	
	
	@Test
	public void addFirestationTest() throws Exception {

		FirestationModel firestationToCreate = new FirestationModel("33", "at home");

		mockMvc.perform(post("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToCreate)))
			.andExpect(status().isOk());
	}


	@Test
	public void PutFirestationTest() throws Exception {
	
		FirestationModel firestationToUpDate = new FirestationModel("333", "at home");

		mockMvc.perform(put("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToUpDate)))
			.andExpect(status().isOk());		
	}

	@Test
	public void deleteFirestationTest() throws Exception {
	
		FirestationModel firestationToDelete = new FirestationModel("333", "at home");

		mockMvc.perform(delete("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToDelete)))
			.andExpect(status().isOk());		
	}

}
