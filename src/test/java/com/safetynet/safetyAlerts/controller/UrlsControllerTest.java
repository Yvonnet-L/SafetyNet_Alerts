package com.safetynet.safetyAlerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.safetynet.safetyAlerts.dao.UrlsDao;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.service.UrlsService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlsControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	UrlsDao urlsDao;

	@Autowired
	UrlsService medicalRecordService;
	
	// ----> U1
	@Test
	public void listPersonsByIdStationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/firestation/  ")).andExpect(status().isOk());
	}
	

	
	// ----> U2
	@Test
	public void listChildByAdress2WithBlanckTest() throws Exception {
		mockMvc.perform(get("/childAlert/  ")).andExpect(status().isOk());
	}	
	
	@Test
	public void listChildByAdress2WithDataTest() throws Exception {
		mockMvc.perform(get("/childAlert/892 Downing Ct")).andExpect(status().isOk());
	}
	
	// ----> U3
	@Test
	public void listPhoneByFirestationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/phoneAlert/  ")).andExpect(status().isOk());
	}
	
	// ----> U4
	@Test
	public void listPersonsByAdressStationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/fire/  ")).andExpect(status().isOk());
	}
	
	@Test
	public void listPersonsByAdressStationWithAddressTest() throws Exception {
		mockMvc.perform(get("/fire/892 Downing Ct")).andExpect(status().isOk());
	}
	
	// ----> U5
	@Test
	public void listFamilysByAdressStationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/flood/stations/  ")).andExpect(status().isOk());
	}
	
	// ----> U6
	@Test
	public void findPersonsWithBlanckTest() throws Exception {
		mockMvc.perform(get("/personInfo/  ")).andExpect(status().isOk());
	}
	
	@Test
	public void findPersonsWithDataTest() throws Exception {
		mockMvc.perform(get("/personInfo/Reginold Walker")).andExpect(status().isOk());
	}
	
	// ----> U7
	@Test
	public void listMailWithBlanckTest() throws Exception {
		mockMvc.perform(get("/communityEmail/  ")).andExpect(status().isOk());
	}
	
	@Test
	public void listMailWithCityTest() throws Exception {
		mockMvc.perform(get("/communityEmail/Culver")).andExpect(status().isOk());
	}
	

}
