package com.safetynet.safetyAlerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.PersonDao;
import com.safetynet.safetyAlerts.model.PersonModel;

@Service
public class PersonService {

	private static Logger logger = LoggerFactory.getLogger(FirestationService.class);

	public List<PersonModel> persons = new ArrayList<>();

	@Autowired
	private PersonDao personDao;

	public List<PersonModel> findAll() {
		logger.info("Traitement de la recherche des Personnes");
		return personDao.findAll();
	}

	public List<PersonModel> findById(String lastName) {
		if (lastName.isEmpty() || lastName.isBlank()) {
			logger.info("** Recherche avortée, id non conforme: {}", lastName);
			return null;
		} else {
			logger.info("Lancement de la recherche pour {}", lastName);
			return personDao.findById(lastName);
		}

	}

	public PersonModel save(PersonModel person) {

		if ((person.getFirstName() != null) & (person.getLastName() != null)) {
			logger.info("Traitement de la demande de création de la personne: {}", person);
			return personDao.save(person);
		} else {
			logger.info("--> Création avortée, format non conforme");
			return null;
		}
	}

	public PersonModel put(PersonModel person) {

		if ((person.getFirstName() != null) & (person.getLastName() != null)) {
			logger.info("Traitement de la demande de modification de la personne: {}", person);
			return personDao.put(person);
		} else {
			logger.info("--> Création avortée, format non conforme");
			return null;
		}
	}

	public List<PersonModel> delete(PersonModel person) {
		if (person != null) {
			logger.info("Traitement de la demande de suppression de {}", person);
			return personDao.delete(person);
		} else {
			logger.info("** Un problème du au format est survenu, suppression impossible pour {} !", person);
			return null;
		}

	}

}
