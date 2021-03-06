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
		logger.info("--> Liste des FireStations: {}", firestations);
		return firestations;
	}

	@Override
	public List<FirestationModel> findById(String station) {

		List<FirestationModel> firestationsSelect = new ArrayList<>();
		
		for (FirestationModel firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				logger.info("--> Ajout d'un résultat à la  liste!");
				firestationsSelect.add(firestation);
			}
		}
		if (firestationsSelect.isEmpty()) {
			logger.info("--->Aucune donnée trouvée pour l'id {}", station);
		} else {
			logger.info("--->Liste des FireStations pour l'id {}: {}", station, firestationsSelect);
		}

		return firestationsSelect;
	}


	@Override
	public List<FirestationModel> deleteById(FirestationModel firestation) {
		List<FirestationModel> fSelect = new ArrayList<>();
		for (FirestationModel f : firestations) {
			if (f.getStation().equals(firestation.getStation()) || f.getAddress().equals(firestation.getAddress())) {
				fSelect.add(f);
			}
		}
		if (fSelect.isEmpty()) {
			logger.info("--> Aucune FireStation Supprimée avec id: {} ou adresse: {} !: {}", firestation.getStation(),
					firestation.getAddress(), fSelect);
		} else
			logger.info("--> Liste des FireStations supprimées avec id: {} ou adresse: {} !: {}", firestation.getStation(),
					firestation.getAddress(), fSelect);
		firestations.removeAll(fSelect);
		return fSelect;

	}

	@Override
	public FirestationModel save(FirestationModel firestation) {
		firestations.add(firestation);
		logger.info("--> FireStation ajoutées: {}", firestation);
		return firestation;
	}

	@Override
	public List<FirestationModel> put(FirestationModel firestation) {
		List<FirestationModel> stationUpDateList = new ArrayList<>();
		if (firestation != null) {
			for (FirestationModel f : firestations) {
				if (f.getAddress().equals(firestation.getAddress())) {
					// firestations.set(firestations.indexOf(f),firestation);;
					f.setStation(firestation.getStation());
					stationUpDateList.add(f);
				}
			}
		}
		if(stationUpDateList.size()>0) {
			logger.info("--> Liste des firestations mise à jour: {}", stationUpDateList);
		}else {
			logger.info("--> Aucune firestation trouvée pour cette adresse", firestation.getAddress());
		}
		return stationUpDateList;
	}

	// ****************** SET ALL FireStations **********************
	@Override
	public void setAllFireStations(List<FirestationModel> listFirestation) {
		this.firestations = listFirestation;

	}

}
