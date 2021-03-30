package com.safetynet.safetyAlerts.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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


@ExtendWith(MockitoExtension.class)
public class UrlsServiceTest {
	
	@InjectMocks
	UrlsService urlsService;
	
	@Mock
	UrlsDaoImpl urlsDao;
	
	@Test
	public void allPersonCoveredByOneStationWithStationNotConformTest() throws Exception {		
		assertThrows(DataNotConformException.class, () ->  urlsService.allPersonCoveredByOneStation("@@@##"));
	}
	
	@Test
	public void allPersonCoveredByOneStationWithStationExistTest() {			
		// GIVEN
		PersonsCoveredByStationU1 personsCoveredByStation = new PersonsCoveredByStationU1();
		// WHEN
		Mockito.when(urlsDao.allPersonCoveredByOneStation("any")).thenReturn(personsCoveredByStation);
		// THEN
		assertTrue(urlsService.allPersonCoveredByOneStation("any").equals(personsCoveredByStation));
	}
	
	@Test
	public void allChildsByAdressWithParentsTest() throws Exception {
		// GIVEN
		ChildsWithParentsU2 childWithParents = new ChildsWithParentsU2();
		// WHEN
		Mockito.when(urlsDao.allChildsByAdressWithParents("any")).thenReturn(childWithParents);		
		// THEN
		assertTrue(urlsService.allChildsByAdressWithParents("any").equals(childWithParents));
	}
	
	@Test
	public void PhoneNumbersForStationTest() throws Exception {
		
		// GIVEN	
		PhoneAlertU3 phoneAlertU3 = new PhoneAlertU3();
		// WHEN
		Mockito.when(urlsDao.phoneNumbersForStation("any")).thenReturn(phoneAlertU3);		
		// THEN
		assertTrue(urlsService.PhoneNumbersForStation("any").equals(phoneAlertU3));
	}
	
	@Test
	public void PersonsByAdressWithStationTest() throws Exception {
		// GIVEN	
		PersonsListU4 personsListU4 = new PersonsListU4();
		// WHEN
		Mockito.when(urlsDao.personsByAdressWithStation("any")).thenReturn(personsListU4);		
		// THEN
		assertTrue(urlsService.PersonsByAdressWithStation("any").equals(personsListU4));
	}
	
	@Test
	public void FamilystByAdressWithStationTest() throws Exception {
		// GIVEN	
		FamilysListU5 familysListU5 = new FamilysListU5();
		// WHEN
		Mockito.when(urlsDao.familystByAdressWithStation("any")).thenReturn(familysListU5);		
		// THEN
		assertTrue(urlsService.familystByAdressWithStation("any").equals(familysListU5));
	}
	
	@Test
	public void infoByPersonTest() throws Exception {
		// GIVEN	
		PersonsListU6 personsListU6 = new PersonsListU6();
		// WHEN
		Mockito.when(urlsDao.infoByPerson("any","any")).thenReturn(personsListU6);		
		// THEN
		assertTrue(urlsService.infoByPerson("any","any").equals(personsListU6));
	}
	
	@Test
	public void allMailOfCityTest() throws Exception {
		// GIVEN	
		MailsByCity mailsByCity = new MailsByCity();
		// WHEN
		Mockito.when(urlsDao.allMailOfCity("any")).thenReturn(mailsByCity);		
		// THEN
		assertTrue(urlsService.allMailOfCity("any").equals(mailsByCity));
	}
}
