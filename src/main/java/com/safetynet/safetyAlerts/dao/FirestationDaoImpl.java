package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.model.FirestationModel;



@Repository
public class FirestationDaoImpl implements FirestationDao {

	private static Logger logger = LoggerFactory.getLogger(FirestationDaoImpl.class);
	
	private List<FirestationModel> firestations = new ArrayList<>();
	
	
	@Override
	public List<FirestationModel> findAll() {
		logger.info("Liste des FireStations: {}", firestations);
		return firestations;
	}

	@Override
	public List<FirestationModel> findById(String station) {
	
		List<FirestationModel> firestationsSelect = new ArrayList<>(); 
				              
            for(FirestationModel firestation : firestations) { 
            	if (firestation.getStation().equals(station)) {
      				firestationsSelect.add(firestation);
            	}
        	}
            logger.info("Liste des FireStations par id {}: {}", station, firestationsSelect);
        	return firestationsSelect;     	
 
	}

	@Override
	public FirestationModel save(FirestationModel firestation) {		
		firestations.add(firestation);	
		logger.info("FireStation ajoutées: {}", firestation);
		return firestation;
	}
	
	//supprimer le mapping d'une caserne "OU" d'une adresse
	@Override
	public List<FirestationModel> delete(FirestationModel firestation) {
		
		List<FirestationModel> fSelect = new ArrayList<>();;
		
		if (firestation != null) {
			for (FirestationModel f : firestations) {
	            if (f.getStation().equals(firestation.getStation()) || f.getAddress().equals(firestation.getAddress())) {
	            	fSelect.add(f);        	
	            }
			}
		}
		if(fSelect.isEmpty()) {
			logger.info("Aucune FireStation Supprimée avec id: {} ou adresse: {} !: {}",firestation.getStation(),firestation.getAddress(), fSelect);
		}else logger.info("Liste des FireStations supprimées avec id: {} ou adresse: {} !: {}",firestation.getStation(),firestation.getAddress(), fSelect);
		firestations.removeAll(fSelect);
		return fSelect;
	     	
	}
	
	// supprimer le mapping d'une caserne ou d'une adresse.
	@Override
	public FirestationModel put(FirestationModel firestation) {
		FirestationModel fireModifie = new FirestationModel();
		if (firestation != null) {
			for(FirestationModel f: firestations) {
				if (f.getAddress().equals(firestation.getAddress()) ) {
					firestations.set(firestations.indexOf(f),firestation);	
					fireModifie = f;
				}
			}		
		}
		logger.info("FireStation Modifiée: {} par {}", fireModifie,firestation);
		return firestation;
	}
	
		// ici nous injectons les données, sans le setAll nous n'aurions aucune données 
	@Override
	public void setAllFireStations(List<FirestationModel> listFirestation) {
		this.firestations = listFirestation;
		
	}

}
