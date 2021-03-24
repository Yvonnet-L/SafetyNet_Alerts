package com.safetynet.safetyAlerts.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.service.FirestationService;
import com.sun.istack.NotNull;

@RestController
public class FirestationController {

	@Autowired
	private FirestationService firestationService;

	//--------------------------------------------------------------------------------------------------------
	@GetMapping("/firestations")
	public List<FirestationModel> listeFirestations() {
		List<FirestationModel> stations = new ArrayList<>();
		stations = firestationService.getFirestations();
		return stations;
	}

	//--------------------------------------------------------------------------------------------------------
	@PostMapping(value = "/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody FirestationModel firestation) {
		
		FirestationModel firestationModel = firestationService.addFirestation(firestation);
		
			 URI location = ServletUriComponentsBuilder
					 .fromCurrentRequest()
					 .path("/{lastName}")
					 .buildAndExpand(firestationModel.getStation())
					 .toUri();			 
		 return ResponseEntity.created(location).build();
		
	}

	//--------------------------------------------------------------------------------------------------------
	@PutMapping(value = "/firestation")
	public ResponseEntity<List<FirestationModel>> upDateFirestation(@RequestBody FirestationModel firestation) {
		
		List<FirestationModel> stationsUpDate = new ArrayList<>();
		stationsUpDate = firestationService.updateFirestation(firestation);
	
		return new ResponseEntity<>(stationsUpDate, HttpStatus.OK);
	}

	//--------------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/firestation")
	public ResponseEntity<List<FirestationModel>> deleteFirestation(@RequestBody FirestationModel firestation) {
		
		List<FirestationModel> fireList = new ArrayList<>();	
		fireList = firestationService.delete(firestation);
		
		return new ResponseEntity<>(fireList, HttpStatus.OK);
	}
}
