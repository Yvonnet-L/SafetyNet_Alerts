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

import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.service.MedicalRecordService;

@RestController
public class MedicalrecordController {

	private static Logger logger = LoggerFactory.getLogger(MedicalrecordController.class);
	
	@Autowired
	private MedicalRecordService medicalrecordService;

	
	@GetMapping(value = "/medicalrecords")
	public List<MedicalrecordModel> listeMedicalrecords() {
		return medicalrecordService.getMedicalrecords();
		
	}
	
	//Medicalrecord/{id}
	@GetMapping(value="/medicalrecord/{firstName} {lastName}")
	public List<MedicalrecordModel> findMedicalrecordsByFirstNameAndLastName(@PathVariable String firstName, 
																			  @PathVariable  String lastName){
		return medicalrecordService.findById(firstName, lastName);
	}
	

	@PostMapping(value="/medicalrecord")
	public MedicalrecordModel ajouterMedicalrecord(@RequestBody MedicalrecordModel medicalrecord) {
		return medicalrecordService.save(medicalrecord);
	}	
	 
	@PutMapping(value="/medicalrecord")
	public MedicalrecordModel modifierMedicalrecord(@RequestBody MedicalrecordModel medicalrecord) {
		return medicalrecordService.put(medicalrecord);
	}	
	
	@DeleteMapping(value="/medicalrecord")
	public MedicalrecordModel supprimerMedicalrecord(@RequestBody MedicalrecordModel medicalrecord) {
		return medicalrecordService.delete(medicalrecord);
	}
	
}
