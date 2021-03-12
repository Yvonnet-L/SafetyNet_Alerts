package com.safetynet.safetyAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlsServiceTest {

	
	@Autowired
	UrlsService urlsService;
	
	
	@Test
	public void allPersonCoveredByOneStationWithStationNotExistTest() throws Exception {
		assertTrue(urlsService.allPersonCoveredByOneStation("StationNotExist").getPerson().isEmpty());
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
		assertTrue(urlsService.PhoneNumbersForStation("StationNotExist").getPhoneList().isEmpty());
	}
	
	@Test
	public void PersonsByAdressWithStationTest() throws Exception {
		assertFalse(urlsService.PersonsByAdressWithStation("892 Downing Ct").getPersons().isEmpty());
		assertTrue(urlsService.PersonsByAdressWithStation("Address Not Exist").getPersons().isEmpty());
	}
	
	@Test
	public void FamilystByAdressWithStationTest() throws Exception {
		assertFalse(urlsService.FamilystByAdressWithStation("4").getPersonsListU5().isEmpty());
		assertTrue(urlsService.FamilystByAdressWithStation("StationNotExist").getPersonsListU5().isEmpty());
	}
	
	@Test
	public void infoByPersonTest() throws Exception {
		assertFalse(urlsService.infoByPerson("Shawna","Stelzer").getPersons().isEmpty());
		assertTrue(urlsService.infoByPerson("firstNameNoExist","lastNameNoExist").getPersons().isEmpty());
	}
	
	@Test
	public void allMailOfCityTest() throws Exception {
		assertFalse(urlsService.allMailOfCity("Culver").getMails().isEmpty());
		assertTrue(urlsService.allMailOfCity("CityNoExist").getMails().isEmpty());
	}
}
