package com.safetynet.safetyAlerts.controller;

import java.net.URI;
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

import com.safetynet.safetyAlerts.exceptions.NameNotConformException;
import com.safetynet.safetyAlerts.exceptions.PersonIntrouvableException;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.PersonService;
import com.safetynet.safetyAlerts.service.StringUtilsService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	StringUtilsService nameValitaded = new StringUtilsService();
	
	@GetMapping("/persons")
	public List<PersonModel> getPersons() {
		return personService.findAll();
	}

	@GetMapping(value = "person/{lastName}")
	public ResponseEntity<List<PersonModel>> findPersons(@PathVariable("lastName") String lastName) throws Exception{	
		
		if (nameValitaded.checkStringName(lastName)) {
			List<PersonModel> listPersons = null;
			listPersons = personService.findById(lastName);	
			if(listPersons == null) {
				throw new PersonIntrouvableException("la person avec le nom " +  lastName + " n'existe pas !");	
			}else {
				return new ResponseEntity<>(listPersons, HttpStatus.OK);
			}			
		}else {
			throw new NameNotConformException("Nom de recherche non conforme !!");
		}
		
	}

	

	@PostMapping(value = "/person")
	public ResponseEntity<Void> ajouterPerson(@Validated @RequestBody PersonModel person) {
		
		 PersonModel personModel= personService.save(person);

		 if(personModel == null) {
			 return ResponseEntity.noContent().build();
		 }else {
			 URI location = ServletUriComponentsBuilder
					 .fromCurrentRequest()
					 .path("/{lastName}")
					 .buildAndExpand(personModel.getLastName())
					 .toUri();			 
		 return ResponseEntity.created(location).build();
		 }
	}

	@PutMapping(value = "/person")
	public ResponseEntity<Void> modifierPersonrecord(@RequestBody PersonModel person) {
		PersonModel personModel= personService.put(person);
		
		if(person == null) {
			 return ResponseEntity.noContent().build();
		 }
		 if (personModel == null) {		
			 return ResponseEntity.notFound().build();
		 }else{
		 URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{lastName}")
				 .buildAndExpand(personModel.getLastName())
				 .toUri();
		 
		 return ResponseEntity.created(location).build();
		 }
	}

	@DeleteMapping(value = "/person")
	public ResponseEntity<String> supprimerPerson(@RequestBody PersonModel person) {
		List<PersonModel> personModelList = personService.delete(person);
		
		if(person == null) {
			 return ResponseEntity.noContent().build();
		 }
		
		if (personModelList.size()>0) {
			 return new ResponseEntity<>("Personne supprimlée ! \n" + personModelList, HttpStatus.OK);		
		}else {
			 return new ResponseEntity<>("Personne non trouvée ! \n" + personModelList, HttpStatus.NOT_FOUND);
		}
		 
		
	}

}
