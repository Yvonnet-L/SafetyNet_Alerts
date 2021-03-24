package com.safetynet.safetyAlerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetyAlerts.dao.UrlsDao;
import com.safetynet.safetyAlerts.model.dto.url1.PersonsCoveredByStationU1;
import com.safetynet.safetyAlerts.service.UrlsService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlsControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UrlsDao urlsDao;

	@Autowired
	UrlsService medicalRecordService;
	
	PersonsCoveredByStationU1 personsCoveredByStation = new PersonsCoveredByStationU1();
	
	// ----> U1 ----------------------------------------------------------------------------------------------
	@Test
	public void listPersonsByIdStationWithStationExistTest() throws Exception {
		mockMvc.perform(get("/firestation/3")).andExpect(status().isOk());
	}
	
	@Test
	public void listPersonsByIdStationWithStationNoExistTest() throws Exception {
		mockMvc.perform(get("/firestation/6666")).andExpect(status().isNotFound());
	}
	
	@Test
	public void listPersonsByIdStationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/firestation/  ")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void listPersonsByIdStationWithStationNotConformTest() throws Exception {
		mockMvc.perform(get("/firestation/@")).andExpect(status().isBadRequest());
	}
	
	// ----> U2 ----------------------------------------------------------------------------------------------
	@Test
	public void listChildByAdressWithDataOkTest() throws Exception {
		mockMvc.perform(get("/childAlert/892 Downing Ct")).andExpect(status().isOk());
	}
	
	@Test
	public void listChildByAdressWithAddressNotExistTest() throws Exception {
		mockMvc.perform(get("/childAlert/99 Street Ct")).andExpect(status().isNotFound());
	}
	
	@Test
	public void listChildByAdressWithBlanckTest() throws Exception {
		mockMvc.perform(get("/childAlert/  ")).andExpect(status().isNotFound());
	}	

	@Test
	public void listChildByAdresWithAddressNotConformTest() throws Exception {
		mockMvc.perform(get("/childAlert/@t home")).andExpect(status().isBadRequest());
	}	
	
	// ----> U3 ----------------------------------------------------------------------------------------------
	@Test
	public void listPhoneByFirestationWithStationExistTest() throws Exception {
		mockMvc.perform(get("/phoneAlert/2")).andExpect(status().isOk());
	}
	
	@Test
	public void listPhoneByFirestationWithStationNoExistTest() throws Exception {
		mockMvc.perform(get("/phoneAlert/6666")).andExpect(status().isNotFound());
	}
	
	@Test
	public void listPhoneByFirestationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/phoneAlert/  ")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void listPhoneByFirestationWithStationNotConformTest() throws Exception {
		mockMvc.perform(get("/phoneAlert/@")).andExpect(status().isBadRequest());
	}
	
	// ----> U4 -----------------------------------------------------------------------------------------------
	@Test
	public void listPersonsByAdressStationWithAddressOkTest() throws Exception {
		mockMvc.perform(get("/fire/892 Downing Ct")).andExpect(status().isOk());
	}
	
	@Test
	public void listPersonsByAdressStationWithAdressNoExistTest() throws Exception {
		mockMvc.perform(get("/fire/99 Street Ct")).andExpect(status().isNotFound());
	}
	
	@Test
	public void listPersonsByAdressStationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/fire/ ")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void listPersonsByAdressStationWithAdressNotConformTest() throws Exception {
		mockMvc.perform(get("/fire/@t home")).andExpect(status().isBadRequest());
	}
	
	
	// ----> U5 ----------------------------------------------------------------------------------------------
	@Test
	public void listFamilysByAdressStationWithStationExistTest() throws Exception {
		mockMvc.perform(get("/flood/stations/4")).andExpect(status().isOk());
	}
	
	@Test
	public void listFamilysByAdressStationWithStationNoExistTest() throws Exception {
		mockMvc.perform(get("/flood/stations/999")).andExpect(status().isNotFound());
	}
		
	@Test
	public void listFamilysByAdressStationWithBlanckTest() throws Exception {
		mockMvc.perform(get("/flood/stations/  ")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void listFamilysByAdressStationWithNotConformAddressTest() throws Exception {
		mockMvc.perform(get("/flood/stations/@66")).andExpect(status().isBadRequest());
	}
	
	// ----> U6 -----------------------------------------------------------------------------------------------
	@Test
	public void findPersonsWithDataExistTest() throws Exception {
		mockMvc.perform(get("/personInfo/Reginold Walker")).andExpect(status().isOk());
	}
	
	@Test
	public void findPersonsWithPersonNoExistTest() throws Exception {
		mockMvc.perform(get("/personInfo/FirstName LastName")).andExpect(status().isNotFound());
	}
	
	@Test
	public void findPersonsWithBlanckTest() throws Exception {
		mockMvc.perform(get("/personInfo/  ")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void findPersonsWithNotConformNamesTest() throws Exception {
		mockMvc.perform(get("/personInfo/Jean-Pierre D'upond")).andExpect(status().isBadRequest());
	}

	
	// ----> U7 ----------------------------------------------------------------------------------------------
	@Test
	public void listMailWithCityExistTest() throws Exception {
		mockMvc.perform(get("/communityEmail/Culver")).andExpect(status().isOk());
	}
		
	@Test
	public void listMailWithCityNoExistTest() throws Exception {
		mockMvc.perform(get("/communityEmail/Paris")).andExpect(status().isNotFound());
	}
	
	@Test
	public void listMailWithBlanckTest() throws Exception {
		mockMvc.perform(get("/communityEmail/  ")).andExpect(status().isBadRequest());
	}
	
	@Test
	public void listMailWithNotConformTest() throws Exception {
		mockMvc.perform(get("/communityEmail/@tCity")).andExpect(status().isBadRequest());
	}
	

}
