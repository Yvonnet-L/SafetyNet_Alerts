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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

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
	FirestationDao firestationDao;

	@Autowired
	FirestationService firestationService;
	

	@Test
	public void getFirestationsTest() throws Exception {

		mockMvc.perform(get("/firestations")).andExpect(status().isOk()).andExpect(jsonPath("$[0].station", is("3")));
	}

	@Test
	public void getFirestationByIdTest() throws Exception {

		mockMvc.perform(get("/firestation/2")).andExpect(status().isOk()).andExpect(jsonPath("$[0].station", is("2")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].address", is("29 15th St")));

		mockMvc.perform(get("/firestation/  ")).andExpect(status().isOk());
	}

	// public ResponseEntity<String> addFirestation(@RequestBody FirestationModel firestation) {

	@Test
	public void addFirestationTest() throws Exception {

	/*	FirestationModel firestationToCreate = new FirestationModel("33","at home");
				
		mockMvc.perform(post("/firestation/")
				  .values(firestationToCreate)
	              .andExpect(status().isOk());*/
		
	
	          
	   
	        
	    
	        
	}

	// public ResponseEntity<String> upDateFirestation(@RequestBody FirestationModel firestation)

	@Test
	public void PutFirestationTest() throws Exception {
		/*FirestationModel firestationToCreate = new FirestationModel("33","at home");
		mockMvc.perform(put("/firestation")
				  .param("station", "10"))
	              .andExpect(status().isOk());*/
	}

	// public ResponseEntity<String> deleteFirestation(@RequestBody FirestationModel firestation)
	@Test
	public void deleteFirestationTest() throws Exception {

		//mockMvc.perform(delete("/firestation")).andExpect(status().isOk());
	}

}
