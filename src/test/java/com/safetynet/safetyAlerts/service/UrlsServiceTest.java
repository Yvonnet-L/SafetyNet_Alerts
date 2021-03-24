package com.safetynet.safetyAlerts.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;

@SpringBootTest
public class UrlsServiceTest {

	
	@Autowired
	UrlsService urlsService;
	
	
	@Test
	public void allPersonCoveredByOneStationWithStationNotExistTest() throws Exception {		
		assertThrows(DataNotFoundException.class, () ->  urlsService.allPersonCoveredByOneStation("StationNotExist"));
	}
	
	@Test
	public void allPersonCoveredByOneStationWithStationExistTest() throws Exception {
		assertFalse(urlsService.allPersonCoveredByOneStation("3").getPerson().isEmpty());
	}
	
	@Test
	public void allChildsByAdressWithParentsTest() throws Exception {
		assertTrue(urlsService.allChildsByAdressWithParents("951 LoneTree Rd").getEnfants().isEmpty());
		assertFalse(urlsService.allChildsByAdressWithParents("951 LoneTree Rd").getParents().isEmpty());
	}
	
	@Test
	public void PhoneNumbersForStationTest() throws Exception {
		assertFalse(urlsService.PhoneNumbersForStation("4").getPhoneList().isEmpty());
		assertThrows(DataNotFoundException.class, () -> urlsService.PhoneNumbersForStation("StationNotExist"));
	}
	
	@Test
	public void PersonsByAdressWithStationTest() throws Exception {
		assertFalse(urlsService.PersonsByAdressWithStation("892 Downing Ct").getPersons().isEmpty());
		assertThrows(DataNotFoundException.class, () -> urlsService.PersonsByAdressWithStation("Address Not Exist"));
	}
	
	@Test
	public void FamilystByAdressWithStationTest() throws Exception {
		assertFalse(urlsService.FamilystByAdressWithStation("4").getPersonsListU5().isEmpty());
		assertThrows(DataNotFoundException.class, () -> urlsService.FamilystByAdressWithStation("StationNotExist"));
	}
	
	@Test
	public void infoByPersonTest() throws Exception {
		assertFalse(urlsService.infoByPerson("Shawna","Stelzer").getPersons().isEmpty());
		assertThrows(DataNotFoundException.class, () -> urlsService.infoByPerson("firstNameNoExist","lastNameNoExist"));
	}
	
	@Test
	public void allMailOfCityTest() throws Exception {
		assertFalse(urlsService.allMailOfCity("Culver").getMails().isEmpty());
		assertThrows(DataNotFoundException.class, () -> urlsService.allMailOfCity("CityNoExist"));
	}
}
