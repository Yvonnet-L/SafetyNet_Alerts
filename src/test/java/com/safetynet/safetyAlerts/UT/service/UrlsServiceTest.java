package com.safetynet.safetyAlerts.UT.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetyAlerts.dao.UrlsDaoImpl;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.dto.url1.PersonsCoveredByStationU1;
import com.safetynet.safetyAlerts.model.dto.url2.ChildsWithParentsU2;
import com.safetynet.safetyAlerts.model.dto.url3.PhoneAlertU3;
import com.safetynet.safetyAlerts.model.dto.url4.PersonsListU4;
import com.safetynet.safetyAlerts.model.dto.url5.FamilysListU5;
import com.safetynet.safetyAlerts.model.dto.url6.PersonsListU6;
import com.safetynet.safetyAlerts.model.dto.url7.MailsByCity;
import com.safetynet.safetyAlerts.service.UrlsService;

 
@ExtendWith(MockitoExtension.class)
public class UrlsServiceTest {
	
	@InjectMocks
	UrlsService urlsService;
	
	@Mock
	UrlsDaoImpl urlsDao;
	
	
	// ----> U1 ---------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U1 - Liste des personnes couvertent par une station")
	class listPersonsByIdStationTests {
	
		@Test
		@DisplayName("Test avec une station qui non conforme")
		public void allPersonCoveredByOneStationWithStationNotConformTest() throws Exception {		
			assertThrows(DataNotConformException.class, () ->  urlsService.allPersonCoveredByOneStation("@@@##"));
		}
		
		@Test
		@DisplayName("Test avec une station qui existe")
		public void allPersonCoveredByOneStationWithStationExistTest() {			
			// GIVEN
			PersonsCoveredByStationU1 personsCoveredByStation = new PersonsCoveredByStationU1();
			// WHEN
			Mockito.when(urlsDao.allPersonCoveredByOneStation("any")).thenReturn(personsCoveredByStation);
			// THEN
			assertTrue(urlsService.allPersonCoveredByOneStation("any").equals(personsCoveredByStation));
		}
		
	}
	
	// ----> U2 ----------------------------------------------------------------------------------------------
	@Nested
	@DisplayName("Tests U2 - Liste des enfants par adresse")
	class listChildByAdresTests {
			
		@Test
		@DisplayName("Test avec une adresse qui existe")
		public void allChildsByAdressWithParentsTest() throws Exception {
			// GIVEN
			ChildsWithParentsU2 childWithParents = new ChildsWithParentsU2();
			// WHEN
			Mockito.when(urlsDao.allChildsByAdressWithParents("any")).thenReturn(childWithParents);		
			// THEN
			assertTrue(urlsService.allChildsByAdressWithParents("any").equals(childWithParents));
		}
		
		@Test
		@DisplayName("Test avec une adresse non-conforme")
		public void allChildsByAdressWithParentsNotConformTest() throws Exception {		
			assertThrows(DataNotConformException.class, () ->  urlsService.allChildsByAdressWithParents("@@@##"));
		}
	}
	
	// ----> U3 ----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U3 - Liste des numéros de téléphone pour une station")
	class listPhoneByFirestationTests {
		
		@Test
		@DisplayName("Test avec une station qui existe")
		public void PhoneNumbersForStationTest() throws Exception {			
			// GIVEN	
			PhoneAlertU3 phoneAlertU3 = new PhoneAlertU3();
			// WHEN
			Mockito.when(urlsDao.phoneNumbersForStation("any")).thenReturn(phoneAlertU3);		
			// THEN
			assertTrue(urlsService.PhoneNumbersForStation("any").equals(phoneAlertU3));
		}
		
		@Test
		@DisplayName("Test avec une station non-conforme")
		public void PhoneNumbersForStationNotConformTest() throws Exception {		
			assertThrows(DataNotConformException.class, () ->  urlsService.PhoneNumbersForStation("@@@##"));
		}
	}
	
	// ----> U4 -----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U4 - Liste des personnes par adress d'une station")
	class listPersonsByAdressStationTests {
	
		@Test
		@DisplayName("Test avec une adresse qui existe")
		public void PersonsByAdressWithStationTest() throws Exception {
			// GIVEN	
			PersonsListU4 personsListU4 = new PersonsListU4();
			// WHEN
			Mockito.when(urlsDao.personsByAdressWithStation("any")).thenReturn(personsListU4);		
			// THEN
			assertTrue(urlsService.PersonsByAdressWithStation("any").equals(personsListU4));
		}
		
		@Test
		@DisplayName("Test avec une adresse non-conforme")
		public void PersonsByAdressWithStationNotConformTest() throws Exception {		
			assertThrows(DataNotConformException.class, () ->  urlsService.allPersonCoveredByOneStation("@@@##"));
		}
	}
	
	// ----> U5 ----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U5 - Liste des familles par adresse pour une station")
	class listFamilysByAdressStationTests {

		@Test
		@DisplayName("Test avec une station qui existe")
		public void FamilystByAdressWithStationTest() throws Exception {
			// GIVEN	
			FamilysListU5 familysListU5 = new FamilysListU5();
			// WHEN
			Mockito.when(urlsDao.familystByAdressWithStation("any")).thenReturn(familysListU5);		
			// THEN
			assertTrue(urlsService.familystByAdressWithStation("any").equals(familysListU5));
		}
		
		@Test
		@DisplayName("Test avec une station non-conforme")
		public void FamilystByAdressWithStationNotConformTest() throws Exception {		
			assertThrows(DataNotConformException.class, () ->  urlsService.familystByAdressWithStation("@@@##"));
		}
	}
	
	// ----> U6 -----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U6 - Liste des personnes pour un prénom et nom")
	class findPersonsTests {
	
		@Test
		@DisplayName("Test avec un prénom/nom qui existent")
		public void infoByPersonTest() throws Exception {
			// GIVEN	
			PersonsListU6 personsListU6 = new PersonsListU6();
			// WHEN
			Mockito.when(urlsDao.infoByPerson("any","any")).thenReturn(personsListU6);		
			// THEN
			assertTrue(urlsService.infoByPerson("any","any").equals(personsListU6));
		}
		
		@Test
		@DisplayName("Test avec un prénom/nom non-conformes")
		public void FamilystByAdressWithStationNotConformTest() throws Exception {		
			assertThrows(DataNotConformException.class, () ->  urlsService.infoByPerson("@@@##","@@@##"));
		}
	}
	
	// ----> U7 ----------------------------------------------------------------------------------------------
	@Nested 
	@DisplayName("Tests U7 - Liste des mails pour une ville")
	class listMailsTests {	
	
		@Test
		@DisplayName("Test avec une ville qui existe")
		public void allMailOfCityTest() throws Exception {
			// GIVEN	
			MailsByCity mailsByCity = new MailsByCity();
			// WHEN
			Mockito.when(urlsDao.allMailOfCity("any")).thenReturn(mailsByCity);		
			// THEN
			assertTrue(urlsService.allMailOfCity("any").equals(mailsByCity));
		}
		
		@Test
		@DisplayName("Test avec une ville non-conforme")
		public void FamilystByAdressWithStationNotConformTest() throws Exception {		
			assertThrows(DataNotConformException.class, () ->  urlsService.allMailOfCity("@@@##"));
		}
	}
}
