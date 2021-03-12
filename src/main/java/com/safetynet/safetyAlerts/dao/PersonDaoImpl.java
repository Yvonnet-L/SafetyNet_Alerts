package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.AgeCalculService;

@Repository
public class PersonDaoImpl implements PersonDao {

	private static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	public List<PersonModel> persons = new ArrayList<>();
	public List<FirestationModel> firestations = new ArrayList<>();
	public List<MedicalrecordModel> medicalrecords = new ArrayList<>();

	@Override
	public List<PersonModel> findAll() {
		logger.info("--> Liste des Personnes trouvées : {}", persons);
		return persons;
	}

	@Override
	public List<PersonModel> findById(String lastName) {

		List<PersonModel> personFind = new ArrayList<>();
		for (PersonModel person : persons) {
			if (person.getLastName().equals(lastName))
				personFind.add(person);
		}
		if (personFind.size() > 0) {
			logger.info("--> Liste des Personnes trouvées pour {}: {}", lastName, personFind);
			return personFind;
		} else {
			logger.info("--> Aucune personne trouvée pour {}", lastName);
			return null;
		}
	}

	@Override
	public PersonModel save(PersonModel person) {

		PersonModel personSelect = null;
		if ((person.getFirstName().isBlank() || person.getLastName().isBlank())) {
			return person;
		} else {
			for (PersonModel p : persons) {
				if (p.getFirstName().equals(person.getFirstName()) & p.getLastName().equals(person.getLastName())) {
					personSelect = p;
				}
			}
			if (personSelect == null) {
				persons.add(person);
				logger.info("-->  Personne crée : {}", person);
				upDateData();
				return person;
			} else {
				logger.info("*** Création impossible une personne pour {} {} est déjà existante !",
						person.getFirstName(), person.getLastName());
				return null;
			}
		}

	}

	@Override
	public List<PersonModel> delete(PersonModel person) {

		List<PersonModel> personSelect = new ArrayList<PersonModel>();
		if ((person.getFirstName().isBlank() || person.getLastName().isBlank())) {
			return null;
		} else {
			for (PersonModel p : persons) {
				if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
					personSelect.add(p);
				}
			}
			persons.removeAll(personSelect);
			if (personSelect.isEmpty()) {
				logger.info("--> Aucune Personne trouvée à ce nom {} {} !", person.getFirstName(),
						person.getLastName());
			} else {
				logger.info("--> Listes des Personnes Supprimées pour {} {}: {}", person.getFirstName(),
						person.getLastName(), personSelect);
				// upDateData();
			}
			return personSelect;
		}
	}

	@Override
	public PersonModel put(PersonModel person) {
		PersonModel personSelect = new PersonModel();
		personSelect = null;
		if ((person.getFirstName().isBlank() || person.getLastName().isBlank())) {
			return person;
		} else {
			for (PersonModel p : persons) {
				if (p.getFirstName().equals(person.getFirstName()) & p.getLastName().equals(person.getLastName())) {
					person.setMedicalrecord(p.getMedicalrecord());
					persons.set(persons.indexOf(p), person);
					personSelect = p;
				}
			}
			if (personSelect == null) {
				logger.info("--> Personne Non trouvé pour {} {} !", person.getFirstName(), person.getLastName());
			} else {
				logger.info("--> Personne Modifiée: {} par {}", personSelect, person);
				upDateData();
			}
			return person;
		}
	}

	

	// ******************** Set All **************************
	
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

	// ********************** updateData *****************************

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
