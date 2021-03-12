package com.safetynet.safetyAlerts.controller;

import java.util.ArrayList;
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

import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.service.FirestationService;

@RestController
public class FirestationController {

	@Autowired
	private FirestationService firestationService;

	@GetMapping("/firestations")
	public List<FirestationModel> listeFirestations() {
		List<FirestationModel> stations = new ArrayList<>();
		stations = firestationService.getFirestations();
		return stations;
	}

	@PostMapping(value = "/firestation")
	public FirestationModel addFirestation(@RequestBody FirestationModel firestation) {
		firestationService.addFirestation(firestation);
		return firestation;
	}

	@PutMapping(value = "/firestation")
	public List<FirestationModel> upDateFirestation(@RequestBody FirestationModel firestation) {
		List<FirestationModel> stationUpDate = new ArrayList<>();
		stationUpDate = firestationService.updateFirestation(firestation);
		return stationUpDate;
	}

	@DeleteMapping(value = "/firestation")
	public ResponseEntity<String> deleteFirestation(@RequestBody FirestationModel firestation) {
		List<FirestationModel> fireList = new ArrayList<>();
		fireList = firestationService.delete(firestation);
		return new ResponseEntity<>("Firestation supprimlée !" + fireList, HttpStatus.OK);
	}

}
