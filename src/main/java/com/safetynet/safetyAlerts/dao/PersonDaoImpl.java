package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.exceptions.DataExistException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.AgeCalculService;
import com.safetynet.safetyAlerts.service.StringUtilsService;

@Repository
public class PersonDaoImpl implements PersonDao {

	private static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	StringUtilsService stringUtilsService = new StringUtilsService();

	public List<PersonModel> persons = new ArrayList<>();
	public List<FirestationModel> firestations = new ArrayList<>();
	public List<MedicalrecordModel> medicalrecords = new ArrayList<>();

	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<PersonModel> findAll() {
		logger.info("--> Liste des Personnes trouvées : {}", persons);
		return persons;
	}
	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<PersonModel> findById(String lastName) {

		List<PersonModel> personFind = new ArrayList<>();
		
		for (PersonModel person : persons) {
			if (person.getLastName().equals(lastName))
				personFind.add(person);
		}
		if (personFind.isEmpty()) {
			throw new DataNotFoundException("la person avec le nom " +  lastName + " n'existe pas !");
		} else {
			logger.info("--> Liste des Personnes trouvées pour {}: {}", lastName, personFind);
			return personFind;
		}
	}
	//--------------------------------------------------------------------------------------------------------
	@Override
	public PersonModel save(PersonModel person) {

		for (PersonModel p : persons) {
			if (p.getFirstName().equals(person.getFirstName()) & p.getLastName().equals(person.getLastName())) {
				
				throw new DataExistException("*** Création impossible une personne pour "+ person.getFirstName() +
						" " + person.getLastName() + " existe déjà !");
			}
		}
		persons.add(person);
		logger.info("-->  Personne crée : {}", person);
		upDateData();
		return person;
	}
	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<PersonModel> delete(PersonModel person) {

		List<PersonModel> personSelect = new ArrayList<PersonModel>();
	
		for (PersonModel p : persons) {
			if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
				personSelect.add(p);
			}
		}
		
		if (personSelect.isEmpty()) {
			throw new DataNotFoundException ("Aucune Personne trouvée au nom " +person.getFirstName() + " " + person.getLastName());
		} else {
			logger.info("--> Listes des Personnes Supprimées pour {} {}: {}", person.getFirstName(),
					person.getLastName(), personSelect);
			persons.removeAll(personSelect);
			upDateData();
		}
		return personSelect;
		
	}
	//--------------------------------------------------------------------------------------------------------
	@Override
	public PersonModel put(PersonModel person) {
		
		List<PersonModel> listPersonFind = new ArrayList<PersonModel>();
		
		for (PersonModel p : persons) {
			if (p.getFirstName().equals(person.getFirstName()) & p.getLastName().equals(person.getLastName())) {
				person.setMedicalrecord(p.getMedicalrecord());
				persons.set(persons.indexOf(p), person);
				logger.info("--> Personne Modifiée: {} par {}", p, person);				
				listPersonFind.add(p);			
			}			
		}
		
		if(listPersonFind.isEmpty()) {
			throw new DataNotFoundException("Personne Non trouvé pour " +person.getFirstName() + "  " + person.getLastName());		
		}else {
			logger.info("--> Personne mise à jour: {}", listPersonFind);
			upDateData();
			return person;
		}	
	}
	//--------------------------------------------------------------------------------------------------------
	
	//--------------------------** Set All **-----------------------------------------------------------------
	
	@Override
	public void setAllPersons(List<PersonModel> listPerson) {
		logger.info("--> SetAllPerson {}", listPerson);
		this.persons = listPerson;
	}

	@Override
	public void setAllMedicalrecords(List<MedicalrecordModel> listMedicalrecord) {
		logger.info("--> SetAllMedic de Person {} ", listMedicalrecord);
		this.medicalrecords = listMedicalrecord;
	}

	@Override
	public void setAllFireStations(List<FirestationModel> listFireStation) {
		logger.info("--> SetAllFirestation de Person {}", listFireStation);
		this.firestations = listFireStation;

	}

	//-------------------------** updateData  **-------------------------------------------------------------

	@Override
	public void upDateData() {
		logger.info("---> Lancement de UpdateData !");
		AgeCalculService ageCalcul = new AgeCalculService();
		int age;
		for (MedicalrecordModel m : medicalrecords) {
			age = 0;
			age = ageCalcul.personCalulateAge(m.getBirthdate(), age);
			m.setAge(age);

			for (PersonModel p : persons) {

				if (p.getFirstName().equals(m.getFirstName()) & p.getLastName().equals(m.getLastName())) {
					p.setMedicalrecord(m);
					p.setAge(age);
				}
				for (FirestationModel f : firestations) {
					if (f.getAddress().equals(p.getAddress())) {
						p.setFirestation(f.getStation());
					}
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
