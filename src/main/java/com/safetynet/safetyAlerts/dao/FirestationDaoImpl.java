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
		logger.info("-->Lancement de la recherche!");	              
            for(FirestationModel firestation : firestations) { 
            	if (firestation.getStation().equals(station)) {
            		logger.info("--> Ajout d'un résultat à la  liste!");
      				firestationsSelect.add(firestation);
            	}
        	}
            if (firestationsSelect.isEmpty()) {
            	logger.info("--->Aucune donnée trouvée pour l'id {}", station);
			}else {
				logger.info("--->Liste des FireStations pour l'id {}: {}", station, firestationsSelect);
			}
				
        	return firestationsSelect;     	
	}


	@Override
	public FirestationModel save(FirestationModel firestation) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<FirestationModel> delete(FirestationModel firestation) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public FirestationModel put(FirestationModel firestation) {
		// TODO Auto-generated method stub
		return null;
	}


   //******************  SET ALL FireStations  **********************
	@Override
	public void setAllFireStations(List<FirestationModel> listFirestation) {
		this.firestations = listFirestation;
		
	}


}
