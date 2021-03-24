package com.safetynet.safetyAlerts.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.PersonService;
import com.safetynet.safetyAlerts.service.StringUtilsService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	StringUtilsService nameValitaded = new StringUtilsService();
	
	//--------------------------------------------------------------------------------------------------------
	@GetMapping("/persons")
	public List<PersonModel> getPersons() {
		return personService.findAll();
	}
	//--------------------------------------------------------------------------------------------------------
	@GetMapping(value = "person/{lastName}")
	public ResponseEntity<List<PersonModel>> findPersonsByLastName(@PathVariable("lastName") String lastName) throws Exception{	
		
			List<PersonModel> listPersons = new ArrayList<>();
			listPersons = personService.findById(lastName);	
		
			return new ResponseEntity<>(listPersons, HttpStatus.OK);	
	}	
	//--------------------------------------------------------------------------------------------------------
	@PostMapping(value = "/person")
	public ResponseEntity<Void> ajouterPerson(@Validated @RequestBody PersonModel person) {
		
		 PersonModel personModel= personService.addPerson(person);

			 URI location = ServletUriComponentsBuilder
					 .fromCurrentRequest()
					 .path("/{lastName}")
					 .buildAndExpand(personModel.getLastName())
					 .toUri();			 
		 return ResponseEntity.created(location).build();
	}
	//--------------------------------------------------------------------------------------------------------
	@PutMapping(value = "/person")
	public ResponseEntity<Void> modifierPersonrecord(@RequestBody PersonModel person) {
		
		 PersonModel personModel;
		 personModel = personService.put(person);
					
		 URI location = ServletUriComponentsBuilder
					 .fromCurrentRequest()
					 .path("/{lastName}")
					 .buildAndExpand(personModel.getLastName())
					 .toUri();		 
		return ResponseEntity.created(location).build();
	}
	//--------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/person")
	public ResponseEntity<List<PersonModel>> supprimerPerson(@RequestBody PersonModel person) {
		
		List<PersonModel> personModelList;
		personModelList = personService.delete(person);
	
		return new ResponseEntity<>(personModelList, HttpStatus.OK);		 
	}
	//--------------------------------------------------------------------------------------------------------
}
