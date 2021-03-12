package com.safetynet.safetyAlerts.controller;

import java.util.List;

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
	public PersonModel ajouterPerson(@RequestBody PersonModel person) {
		return personService.save(person);
	}

	@PutMapping(value = "/person")
	public PersonModel modifierPersonrecord(@RequestBody PersonModel person) {
		return personService.put(person);
	}

	@DeleteMapping(value = "/person")
	public List<PersonModel> supprimerPerson(@RequestBody PersonModel person) {
		return personService.delete(person);
		
	}

}
