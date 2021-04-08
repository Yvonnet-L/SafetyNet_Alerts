package com.safetynet.safetyAlerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetyAlerts.model.dto.url1.PersonsCoveredByStationU1;
import com.safetynet.safetyAlerts.model.dto.url2.ChildsWithParentsU2;
import com.safetynet.safetyAlerts.model.dto.url3.PhoneAlertU3;
import com.safetynet.safetyAlerts.model.dto.url4.PersonsListU4;
import com.safetynet.safetyAlerts.model.dto.url5.FamilysListU5;
import com.safetynet.safetyAlerts.model.dto.url6.PersonsListU6;
import com.safetynet.safetyAlerts.model.dto.url7.MailsByCity;
import com.safetynet.safetyAlerts.service.UrlsService;

@RestController
public class UrlsController {

	@Autowired
	private UrlsService urlsService;
	
	//-------------------------------------------------------------------------------------------------------
	// Url 1 --> http://localhost:8080/firestation?stationNumber=<station_number>
	@GetMapping(value = "firestation")
	public PersonsCoveredByStationU1 listPersonsByIdStation(@RequestParam("stationNumber") String stationNumber) {
		return urlsService.allPersonCoveredByOneStation(stationNumber);
	}

	//-------------------------------------------------------------------------------------------------------
	// Url 2 --> http://localhost:8080/childAlert?address=<address>
	@GetMapping(value = "childAlert")
	public ChildsWithParentsU2 listChildByAdress2(@RequestParam("address") String address) {
		return urlsService.allChildsByAdressWithParents(address);
	}

	//-------------------------------------------------------------------------------------------------------
	// Url 3 --> http://localhost:8080/phoneAlert?firestation=<firestationNumber>
	@GetMapping(value = "phoneAlert")
	public PhoneAlertU3 listPhoneByFirestation(@RequestParam("firestation") String firestation_number) {
		return urlsService.PhoneNumbersForStation(firestation_number);
	}

	//-------------------------------------------------------------------------------------------------------
	// Url 4 --> http://localhost:8080/fire?address=<address>
	@GetMapping(value = "fire")
	public PersonsListU4 listPersonsByAdressStation(@RequestParam("address") String address) {
		return urlsService.PersonsByAdressWithStation(address);
	}

	//-------------------------------------------------------------------------------------------------------
	// Url 5 --> http://localhost:8080/flood/stations?stations=<a list of station_numbers> FamilystByAdressWithStation
	@GetMapping(value = "flood/stations")
	public FamilysListU5 listFamilysByAdressStation(@RequestParam("stations") String stations) {
		return urlsService.familystByAdressWithStation(stations);
	}

	//-------------------------------------------------------------------------------------------------------
	// Url 6 --> http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@GetMapping(value = "personInfo")
	public PersonsListU6 findPersons(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		return urlsService.infoByPerson(firstName, lastName);
	}

	//-------------------------------------------------------------------------------------------------------
	// Url 7 --> http://localhost:8080/communityEmail?city=<city>
	@GetMapping(value = "communityEmail")
	public MailsByCity listMail(@RequestParam("city") String city) {
		return urlsService.allMailOfCity(city);
	}
}
