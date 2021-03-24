package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.exceptions.DataExistException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.PersonModel;

@Repository
public class FirestationDaoImpl implements FirestationDao {
	
	
	PersonDao personDao;
	
	FirestationDao fireStationDao;

	MedicalRecordDao medicalRecordsDao;
	
	DataDao dataDao;

	private static Logger logger = LoggerFactory.getLogger(FirestationDaoImpl.class);

	private List<FirestationModel> firestations = new ArrayList<>();
	
	private List<PersonModel> persons = new ArrayList<>();
	
	
	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<FirestationModel> findAll() {
		
		logger.info("--> Liste des FireStations: {}", firestations);
		return firestations;
	}

	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<FirestationModel> deleteById(FirestationModel firestation) {
		
		List<FirestationModel> fireSelect = new ArrayList<>();
		
		for (FirestationModel f : firestations) {
				if ( (firestation.getStation().equals(f.getStation())) || (firestation.getAddress().contentEquals(f.getAddress())) ) {
					fireSelect.add(f);
				}
			}
				
		if (fireSelect.isEmpty()) {
			throw new DataNotFoundException ("Aucune FireStation trouvée pour " + firestation.getStation() + " " + 
												firestation.getAddress());
		} else {
			logger.info("--> Liste des FireStations supprimées avec id: {} ou adresse: {} !: {}", firestation.getStation(),
					firestation.getAddress(), fireSelect);
			firestations.removeAll(fireSelect);
			updateData();
		}
		return fireSelect;

	}
	
	//--------------------------------------------------------------------------------------------------------
	@Override
	public FirestationModel save(FirestationModel firestation) {
		
		for (FirestationModel f : firestations) {			
				if(firestation.getStation().equals(f.getAddress())){
					throw new DataExistException("*** Création impossible, cette adresse de fireStation existe déjà");	
				}		
		}
		firestations.add(firestation);
		logger.info("--> FireStation ajoutées: {}", firestation);
		updateData();
		return firestation;
	}

	
	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<FirestationModel> put(FirestationModel firestation) {
		
		List<FirestationModel> stationUpDateList = new ArrayList<>();
		
			for (FirestationModel f : firestations) {
				if (firestation.getAddress().equals(f.getAddress())) {
					f.setStation(firestation.getStation());
					stationUpDateList.add(f);
				}
			}
		
		if(stationUpDateList.isEmpty()) {
			throw new DataNotFoundException("** Adresse Non trouvée");
		}else {
			logger.info("--> Liste des firestations mise à jour: {}", stationUpDateList);
			updateData();
			return stationUpDateList;
		}
		
	}

	
	//--------------------------** Set All **-----------------------------------------------------------------
	//@Override
	public void setAllFireStations(List<FirestationModel> listFirestation) {
		logger.info("--> SetAllFire {}", listFirestation);
		this.firestations = listFirestation; 

	}
	
	@Override
	public void setAllPersons(List<PersonModel> listPerson) {
		logger.info("--> SetAllPerson de Fire {}", listPerson);
		this.persons = listPerson;

	}
	
	//-------------------------** updateData  **-------------------------------------------------------------*
	
	@Override 
	public void updateData() {
		logger.info("---> Lancement de UpdateData !");		
			for (PersonModel p : persons) {
				p.setFirestation(null);
				for (FirestationModel f : firestations) {
					if (f.getAddress().equals(p.getAddress())) {
						p.setFirestation(f.getStation());					
					}
				}
			}
			for (FirestationModel f : firestations) {
				List<PersonModel> listPersonsFire = new ArrayList<PersonModel>();
				int nbPerson = 0;
				for (PersonModel p : persons) {
					if (f.getAddress().equals(p.getAddress())) {
						listPersonsFire.add(p);
						nbPerson++;
					}
				}
				f.setPerson(listPersonsFire);
				f.setNbPerson(nbPerson);
			}
		logger.info("----> Update terminé, données mises à jour !");
	}
	
}
