package com.safetynet.safetyAlerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.PersonService;

@RestController
public class PersonController {

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/persons")
	public List<PersonModel> getPersons() {
		return personService.findAll();
	}

	@GetMapping(value = "person/{lastName}")
	public List<PersonModel> findPersons(@PathVariable("lastName") String lastName) {
		return personService.findById(lastName);
	}

	@PostMapping(value = "/person")
	public void ajouterPerson(@RequestBody PersonModel person) {
		personService.save(person);
	}

	@PutMapping(value = "/person")
	public void modifierPersonrecord(@RequestBody PersonModel person) {
		personService.put(person);
	}

	@DeleteMapping(value = "/person")
	public void supprimerPerson(@RequestBody PersonModel person) {
		personService.delete(person);
	}

	// ************** URLS *****************************
	/*
	// Url 1 --> http://localhost:8080/firestation?stationNumber=<station_number>
	@GetMapping(value = "firestation/{stationNumber}")
	public PersonsCoveredByStationU1 listPersonsByIdStation(@PathVariable("stationNumber") String station_number) {
		return personDao.allPersonCoveredByOneStation(station_number);
	}

	// Url 2 --> http://localhost:8080/childAlert?address=<address>
	@GetMapping(value = "childAlert/{address}")
	public Collection<List<String>> listChildByAdress(@PathVariable("address") String address) {
		logger.info("Entrée dans recherche des enfant pour cette adresse {}: ", address);
		return (Collection<List<String>>) personDao.allChildsByAdressWithParents(address);
	}

	@GetMapping(value = "childAlert2/{address}")
	public ChildsWithParentsU2 listChildByAdress2(@PathVariable("address") String address) {
		logger.info("Entrée dans recherche des enfant pour cette adresse {}: ", address);
		return (ChildsWithParentsU2) personDao.allChildsByAdressWithParents2(address);
	}

	// Url 3 --> http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@GetMapping(value = "phoneAlert/{firestation}")
	public PhoneAlert listPhoneByFirestation(@PathVariable("firestation") String firestation_number) {
		return personDao.PhoneNumbersForStation(firestation_number);
	}

	// Url 4 --> http://localhost:8080/fire?address=<address>
	@GetMapping(value = "fire/{address}")
	public PersonsListU4 listPersonsByAdressStation(@PathVariable("address") String address) {
		return personDao.PersonsByAdressWithStation(address);
	}
	
	// Url 5 --> http://localhost:8080/flood/stations?stations=<a list of station_numbers> FamilystByAdressWithStation
	@GetMapping(value = "flood/stations/{station}")
	public FamilysListU5 listFamilysByAdressStation(@PathVariable("station") String station) {
		return personDao.FamilystByAdressWithStation(station);
	}
 
	// Url 6 --> http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@GetMapping(value = "personInfo/{firstName} {lastName}")
	public PersonsListU6 findPersons(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return personDao.infoByPerson(firstName, lastName);
	}

	// Url 7 --> http://localhost:8080/communityEmail?city=<city>
	@GetMapping(value = "communityEmail/{city}")
	public MailsByCity listMail(@PathVariable("city") String city) {
		return personDao.allMailOfCity(city);
	}

	*/
}
