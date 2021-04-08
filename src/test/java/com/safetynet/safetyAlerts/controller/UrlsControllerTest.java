package com.safetynet.safetyAlerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
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
	
	// ----> U1 ---------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U1 - Liste des personnes pour une station")
	class listPersonsByIdStationTests {
		
		@Test	
		@DisplayName("Test avec une station qui existe")
		public void listPersonsByIdStationWithStationExistTest() throws Exception {
			mockMvc.perform(get("/firestation?stationNumber=3")).andExpect(status().isOk());
		}
	
		@Test
		@DisplayName("Test avec une station qui n'existe pas")
		public void listPersonsByIdStationWithStationNoExistTest() throws Exception {
			mockMvc.perform(get("/firestation?stationNumber=stationNoExist")).andExpect(status().isNotFound());
		}
		
		@Test
		@DisplayName("Test avec une station qui est un blanc")
		public void listPersonsByIdStationWithBlanckTest() throws Exception {
			mockMvc.perform(get("/firestation?stationNumber= ")).andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("Test avec une station non conforme")
		public void listPersonsByIdStationWithStationNotConformTest() throws Exception {
			mockMvc.perform(get("/firestation?stationNumber=@")).andExpect(status().isBadRequest());
		}
	}
	
	// ----> U2 ----------------------------------------------------------------------------------------------
	@Nested
	@DisplayName("Tests U2 - Liste des enfants par adresse")
	class listChildByAdresTests {
		
		@Test
		@DisplayName("Test avec une adresse qui existe")
		public void listChildByAdressWithDataOkTest() throws Exception {
			mockMvc.perform(get("/childAlert?address=892 Downing Ct")).andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("Test avec une adresse qui n'existe pas")
		public void listChildByAdressWithAddressNotExistTest() throws Exception {
			mockMvc.perform(get("/childAlert?address=99 Street Ct")).andExpect(status().isNotFound());
		}
		
		@Test
		@DisplayName("Test avec une adresse blanche")
		public void listChildByAdressWithBlanckTest() throws Exception {
			mockMvc.perform(get("/childAlert?address=  ")).andExpect(status().isNotFound());
		}	
	
		@Test
		@DisplayName("Test avec une adresse non conforme")
		public void listChildByAdresWithAddressNotConformTest() throws Exception {
			mockMvc.perform(get("/childAlert?address=@t home")).andExpect(status().isBadRequest());
		}	
	}
	// ----> U3 ----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U3 - Liste des numéros de téléphone pour une station")
	class listPhoneByFirestationTests {
		
		@Test
		@DisplayName("Test avec une station qui existe")
		public void listPhoneByFirestationWithStationExistTest() throws Exception {
			mockMvc.perform(get("/phoneAlert?firestation=2")).andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("Test avec une station qui n'existe pas")
		public void listPhoneByFirestationWithStationNoExistTest() throws Exception {
			mockMvc.perform(get("/phoneAlert?firestation=6666")).andExpect(status().isNotFound());
		}
		
		@Test
		@DisplayName("Test avec une station blanche")
		public void listPhoneByFirestationWithBlanckTest() throws Exception {
			mockMvc.perform(get("/phoneAlert?firestation=  ")).andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("Test avec une station non-conforme")
		public void listPhoneByFirestationWithStationNotConformTest() throws Exception {
			mockMvc.perform(get("/phoneAlert?firestation=@")).andExpect(status().isBadRequest());
		}
	}
	// ----> U4 -----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U4 - Liste des personnes par adresses d'une station")
	class listPersonsByAdressStationTests {
		
		@Test
		@DisplayName("Test avec une adresse qui existe")
		public void listPersonsByAdressStationWithAddressOkTest() throws Exception {
			mockMvc.perform(get("/fire?address=892 Downing Ct")).andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("Test avec une adresse qui n'existe pas")
		public void listPersonsByAdressStationWithAdressNoExistTest() throws Exception {
			mockMvc.perform(get("/fire?address=99 Street Ct")).andExpect(status().isNotFound());
		}
		
		@Test
		@DisplayName("Test avec une adresse blanche")
		public void listPersonsByAdressStationWithBlanckTest() throws Exception {
			mockMvc.perform(get("/fire?address= ")).andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("Test avec une adresse non-conforme")
		public void listPersonsByAdressStationWithAdressNotConformTest() throws Exception {
			mockMvc.perform(get("/fire?address=@t home")).andExpect(status().isBadRequest());
		}
	
	}
	// ----> U5 ----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U5 - Liste des familles par adresse pour une station")
	class listFamilysByAdressStationTests {
		
		@Test
		@DisplayName("Test avec une station qui existe")
		public void listFamilysByAdressStationWithStationExistTest() throws Exception {
			mockMvc.perform(get("/flood/stations?stations=4")).andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("Test avec une station qui n'existe pas")
		public void listFamilysByAdressStationWithStationNoExistTest() throws Exception {
			mockMvc.perform(get("/flood/stations?stations=stationNotExist")).andExpect(status().isNotFound());
		}
			
		@Test
		@DisplayName("Test avec une station blanche")
		public void listFamilysByAdressStationWithBlanckTest() throws Exception {
			mockMvc.perform(get("/flood/stations?stations=  ")).andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("Test avec une station non-conforme")
		public void listFamilysByAdressStationWithNotConformAddressTest() throws Exception {
			mockMvc.perform(get("/flood/stations?stations=@66")).andExpect(status().isBadRequest());
		}
	}
	// ----> U6 -----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U6 - Liste des personnes pour un prénom et nom")
	class findPersonsTests {
		
		@Test
		@DisplayName("Test avec un prénom/nom qui existent")
		public void findPersonsWithDataExistTest() throws Exception {
			mockMvc.perform(get("/personInfo?firstName=Reginold&lastName=Walker")).andExpect(status().isOk());
		}
		
		@Test
		@DisplayName("Test avec un prénom/nom qui n'existent pas")
		public void findPersonsWithPersonNoExistTest() throws Exception {
			mockMvc.perform(get("/personInfo?firstName=FirstNameNotExist&lastName=LastNameNotExist")).andExpect(status().isNotFound());
		}
		
		@Test
		@DisplayName("Test avec un prénom/nom blancs")
		public void findPersonsWithBlanckTest() throws Exception {
			mockMvc.perform(get("/personInfo?firstName=  &lastName= ")).andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("Test avec un prénom/nom non-conformes")
		public void findPersonsWithNotConformNamesTest() throws Exception {
			mockMvc.perform(get("/personInfo?firstName=Jean-Pierre&lastName=D'upond")).andExpect(status().isBadRequest());
		}

	}
	// ----> U7 ----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U7 - Liste des mails pour une ville")
	class listMailsTests {
		
		@Test
		@DisplayName("Test avec une ville qui existe")
		public void listMailsWithCityExistTest() throws Exception {
			mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk());
		}
			
		@Test
		@DisplayName("Test avec une ville qui n'existe pas")
		public void listMailsWithCityNoExistTest() throws Exception {
			mockMvc.perform(get("/communityEmail?city=cityNoExist")).andExpect(status().isNotFound());
		}
		
		@Test
		@DisplayName("Test avec une ville blanche")
		public void listMailsWithBlanckTest() throws Exception {
			mockMvc.perform(get("/communityEmail?city=  ")).andExpect(status().isBadRequest());
		}
		
		@Test
		@DisplayName("Test avec une ville non-conforme")
		public void listMailsWithNotConformTest() throws Exception {
			mockMvc.perform(get("/communityEmail?city=@tCity")).andExpect(status().isBadRequest());
		}
	}

}
