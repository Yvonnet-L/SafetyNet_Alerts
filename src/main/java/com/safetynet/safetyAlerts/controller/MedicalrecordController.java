package com.safetynet.safetyAlerts.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.service.MedicalRecordService;

@RestController
public class MedicalrecordController {

	@Autowired
	private MedicalRecordService medicalrecordService;

	@GetMapping(value = "/medicalrecords")
	public List<MedicalrecordModel> listeMedicalrecords() {
		return medicalrecordService.getMedicalrecords();
	}

	@GetMapping(value = "/medicalrecord/{firstName} {lastName}")
	public ResponseEntity<List<MedicalrecordModel>> findMedicalrecordsByFirstNameAndLastName(@PathVariable String firstName,
			@PathVariable String lastName) throws DataNotFoundException {
		
		List<MedicalrecordModel> listMedic = medicalrecordService.findById(firstName, lastName);
		
		if (listMedic == null) {
			throw new DataNotFoundException("Veuillez entrer des données conformes !");
		} else {
			if (listMedic.isEmpty()) {
				throw new DataNotFoundException("la person avec le nom " + firstName + " " + lastName + " n'existe pas !");
			}
			return new ResponseEntity<>(listMedic, HttpStatus.OK);
		}

	}

	@PostMapping(value = "/medicalrecord")
	public ResponseEntity<Void> ajouterMedicalrecord(@RequestBody MedicalrecordModel medicalrecord) {
	
		MedicalrecordModel medicalRecord = medicalrecordService.addMedicalRecord(medicalrecord);
		
		 if(medicalRecord == null) {
			 return ResponseEntity.noContent().build();
		 }else {
			 URI location = ServletUriComponentsBuilder
					 .fromCurrentRequest()
					 .path("/{lastName}")
					 .buildAndExpand(medicalRecord.getLastName())
					 .toUri();			 
		 return ResponseEntity.created(location).build();
		 }
	}

	@PutMapping(value = "/medicalrecord")
	public MedicalrecordModel modifierMedicalrecord(@RequestBody MedicalrecordModel medicalrecord) {
		return medicalrecordService.upDateMedicalrecord(medicalrecord);
	}

	@DeleteMapping(value = "/medicalrecord")
	public MedicalrecordModel supprimerMedicalrecord(@RequestBody MedicalrecordModel medicalrecord) {
		return medicalrecordService.deleteMedicalRecord(medicalrecord);
	}

}
