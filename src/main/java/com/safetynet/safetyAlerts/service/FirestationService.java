package com.safetynet.safetyAlerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.model.FirestationModel;

@Service
public class FirestationService {

	
	private static Logger logger = LoggerFactory.getLogger(FirestationService.class);
	
	@Autowired
	private FirestationDao firestationDao;

	
	
	public List<FirestationModel> getFirestations() {		
		logger.info("Lancement de la recherche de toutes les FireStations");		
		return firestationDao.findAll();	
	}


	public List<FirestationModel> findById(String station) {        
            if (station.isEmpty() || station.isBlank()) {
            	logger.info("** Recherche avortée, id station null : {}",station);
            	return null;
            }else {
            	logger.info("Lancement de la recherche pour la station: {}",station);
            	return firestationDao.findById(station);
            }
      	     	
	}

	
	public List<FirestationModel> delete(FirestationModel firestation) {
		List<FirestationModel> firesList = new ArrayList<>();
		
		if (firestation != null) {
			logger.info( "Lancement de la suppression !");
			firesList = firestationDao.deleteById(firestation);
		}
		else {			
			logger.info( "** Attention adress non conforme !");   			
		}	
		return firesList;		
	}


	public List<FirestationModel> updateFirestation(FirestationModel firestation){
		List<FirestationModel> firesList= new ArrayList<>();	
		if (firestation != null) {
			if (firestation.getAddress() != null) {
			logger.info( "Lancement de la mise à jour !");
			firesList = firestationDao.put(firestation);
			}
		}
		else {			
			logger.info( "** Attention adress non conforme !");
		}	
		return firesList;			
	}


	public FirestationModel addFirestation(FirestationModel firestation) {
		logger.info("Lancement de la mise à jour de la FireStation");
		firestationDao.save(firestation);
		return firestation;
	}
}
