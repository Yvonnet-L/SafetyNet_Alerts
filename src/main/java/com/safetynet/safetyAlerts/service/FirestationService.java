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

	
	
	public Iterable<FirestationModel> getFirestations() {		
		logger.info("Lancement de la recherche de toutes les FireStations");		
		return firestationDao.findAll();	
	}


	public List<FirestationModel> findById(String station) {
		
            logger.info("Vérification id avant traitement id: {}",station);
            if (station.isEmpty() || station.isBlank()) {
            	logger.info("->Recherche avortée, id station null : {}",station);
            	return null;
            }else {
            	return firestationDao.findById(station);
            }
      	     	
	}

	
	public List<FirestationModel> delete(FirestationModel firestation) {
		List<FirestationModel> firesList = new ArrayList<>();
		if (firestation != null) {
			firesList = firestationDao.deleteById(firestation);
		}
		else {			
			logger.info( "Attention station et adress non conforme !");     
		}	
		return firesList;		
	}


	public List<FirestationModel> updateFirestation(FirestationModel firestation) {
		List<FirestationModel> station= new ArrayList<>();
		station = firestationDao.put(firestation);
		return station;
		
	}


	public FirestationModel addFirestation(FirestationModel firestation) {
		firestationDao.save(firestation);
		return firestation;
	}
}
