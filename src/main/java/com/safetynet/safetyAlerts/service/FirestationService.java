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

	private List<FirestationModel> firestations = new ArrayList<>();


	
	
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

		List<FirestationModel> fSelect = new ArrayList<>();
		;

		if (firestation != null) {
			for (FirestationModel f : firestations) {
				if (f.getStation().equals(firestation.getStation())
						|| f.getAddress().equals(firestation.getAddress())) {
					fSelect.add(f);
				}
			}
		}
		if (fSelect.isEmpty()) {
			logger.info("Aucune FireStation Supprimée avec id: {} ou adresse: {} !: {}", firestation.getStation(),
					firestation.getAddress(), fSelect);
		} else
			logger.info("Liste des FireStations supprimées avec id: {} ou adresse: {} !: {}", firestation.getStation(),
					firestation.getAddress(), fSelect);
		firestations.removeAll(fSelect);
		return fSelect;

	}

}
