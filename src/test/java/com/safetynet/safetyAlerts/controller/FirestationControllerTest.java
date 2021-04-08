package com.safetynet.safetyAlerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("Test sur le getFirestation")
	public void getFirestationsTest() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk());	
	}
	
	@Test
	@DisplayName("Test sur l'ajout d'une firestaion conforme")
	public void addFirestationTest() throws Exception {
				
		FirestationModel firestationToCreate = new FirestationModel("stationTest", "address Test");

		mockMvc.perform(post("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToCreate)))
			.andExpect(status().isCreated());		
	}
	
	@Test
	@DisplayName("Test sur l'ajout d'une firestaion null")
	public void addFirestationBadRequestWithNullTest() throws Exception {
	
		FirestationModel firestationToCreate = null;

		mockMvc.perform(post("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToCreate)))
			.andExpect(status().isBadRequest());		
	}


	@Test
	@DisplayName("Test sur l'upDate d'une firestaion")
	public void PutFirestationTest() throws Exception {
	
		FirestationModel firestationToCreate = new FirestationModel("stationTest", "address Test");
		firestationDao.save(firestationToCreate);

		FirestationModel firestationToUpDate = new FirestationModel("stationTest2", "address Test");

		mockMvc.perform(put("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToUpDate)))
			.andExpect(status().isOk());		
	}
	
	@Test
	@DisplayName("Test sur la suppression d'une firestaion conforme")
	public void deleteFirestationTest() throws Exception {
	
		FirestationModel firestationToDelete = new FirestationModel("stationTest2", "address Test");

		mockMvc.perform(delete("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToDelete)))
			.andExpect(status().isOk());		
	}
	
	@Test
	@DisplayName("Test sur la suppression d'une firestaion null")
	public void deleteFirestationBadRequestWithNullTest() throws Exception {
	
		FirestationModel firestationToDelete = null;

		mockMvc.perform(delete("/firestation/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firestationToDelete)))
			.andExpect(status().isBadRequest());		
	}

}
