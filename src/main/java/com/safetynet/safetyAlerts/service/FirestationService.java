package com.safetynet.safetyAlerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.FirestationModel;

@Service
public class FirestationService {

	private static Logger logger = LoggerFactory.getLogger(FirestationService.class);
	
	StringUtilsService stringUtilsService = new StringUtilsService();
	
	@Autowired
	private FirestationDao firestationDao;

	
	//--------------------------------------------------------------------------------------------------------
	public List<FirestationModel> getFirestations() {		
		logger.info("Lancement de la recherche de toutes les FireStations");		
		return firestationDao.findAll();	
	}

	//--------------------------------------------------------------------------------------------------------
	public List<FirestationModel> delete(FirestationModel firestation) {
		
		List<FirestationModel> firesList = new ArrayList<>();
		Boolean fireOk = firestationTestData(firestation);
		
		if (fireOk) {
			logger.info( "Lancement de la suppression !");
			return firesList = firestationDao.deleteById(firestation);
		}else throw new DataNotConformException("*** Update avorté, adresse ou station non conforne");
				
	}

	//--------------------------------------------------------------------------------------------------------
	public List<FirestationModel> updateFirestation(FirestationModel firestation){
		
		Boolean fireOk = firestationTestData(firestation);		
		List<FirestationModel> firesList= new ArrayList<>();
		
			if (fireOk) { 
				logger.info( "Lancement de la mise à jour !");
				firesList = firestationDao.put(firestation);
				return firesList;
			}else throw new DataNotConformException("*** Création avortée, adresse ou station non conforne");		
	}

	//--------------------------------------------------------------------------------------------------------
	public FirestationModel addFirestation(FirestationModel firestation) {
			
		Boolean fireOk = firestationTestData(firestation);
				
		if ( fireOk ) {			
			logger.info("Lancement création de la FireStation");
			firestationDao.save(firestation);
			return firestation;
		}else throw new DataNotConformException("** Données entrantes non conformes");
		
	}
		
	//--------------------** Utility method **--------------------------------------------------------
	
		public boolean firestationTestData(FirestationModel firestation) {
			
			Boolean stationOk = false, addressOk = false;
			
			if(firestation.getStation() != null ) { stationOk = stringUtilsService.checkStringStation(firestation.getStation()); 
			}
			if(firestation.getAddress() != null ) { addressOk = stringUtilsService.checkStringAddress(firestation.getAddress()); 
			}
			
			if (stationOk & addressOk ) {
				return true;
			} else {
				return false;
			}
		}
}
