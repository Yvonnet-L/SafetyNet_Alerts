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

import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.model.FirestationModel;



@RestController
public class FirestationController {

	@Autowired
	private FirestationDao firestationDao;
	

	//Firestations
	@GetMapping("/firestations")
	public List<FirestationModel> listeFirestations(){
		return firestationDao.findAll();
	}

	
	//Firestation/{id}
	@GetMapping(value="firestation/{station}")
	public List<FirestationModel> findStationByMatricul(@PathVariable("station") String station) {
		return firestationDao.findById(station);
	}
	
	
	@PostMapping(value="/firestation")
	public void ajouterFirestation(@RequestBody FirestationModel firestation) {
		firestationDao.save(firestation);
	}
	
	@PutMapping(value="/firestation")
	public void modifierFirestation(@RequestBody FirestationModel firestation) {
		firestationDao.put(firestation);
	}	
	
	@DeleteMapping(value="/firestation")
	public void supprimerFirestation(@RequestBody FirestationModel firestation) {
		firestationDao.delete(firestation);
	}
	
}
