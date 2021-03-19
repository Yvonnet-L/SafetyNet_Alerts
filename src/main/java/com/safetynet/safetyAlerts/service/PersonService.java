package com.safetynet.safetyAlerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.PersonDao;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;

@Service
public class PersonService {

	private static Logger logger = LoggerFactory.getLogger(FirestationService.class);

	public List<PersonModel> persons = new ArrayList<>();
	
	StringUtilsService stringUtilsService = new StringUtilsService();

	@Autowired
	private PersonDao personDao;

	public List<PersonModel> findAll() {
		logger.info("Traitement de la recherche des Personnes");
		return personDao.findAll();
	}

	public List<PersonModel> findById(String lastName) throws Exception {
		
		logger.info("Vérification de la validité du nom recherché: {}", lastName);
		
		if (!stringUtilsService.checkStringName(lastName)) {
			throw new DataNotConformException("Nom de recherche non conforme !!");
		} else {
			logger.info("-> {} est conforme", lastName);
			logger.info("-->Lancement de la recherche !");
			return personDao.findById(lastName);
		}

	}
	public PersonModel addPerson(PersonModel person) {
		
		Boolean namesOk = personTestNames(person);

		if (namesOk){
			logger.info("Traitement de la demande de création de la personne: {}", person);
			return personDao.save(person);
		} else throw new DataNotConformException("*** Création avortée, nom non conforme !");

	}

	public PersonModel put(PersonModel person) {
		
		Boolean namesOk = personTestNames(person);
	
		if (namesOk) {	
			logger.info("Traitement de la demande de modification de la personne: {}", person);
			return personDao.put(person);
		} else throw new DataNotConformException("*** Update avorté, format non conforme !");	
	}
		

	public List<PersonModel> delete(PersonModel person) {
		
		Boolean namesOk = personTestNames(person);
		
		if (namesOk) {	
			logger.info("Traitement de la demande de suppression de {}", person);
			return personDao.delete(person);
		} else throw new DataNotConformException("*** Update avorté, format non conforme !");
	}
	
	//--------------------** Utility method **--------------------------------------------------------
	
	public boolean personTestNames(PersonModel person) {
		
		Boolean firstNameOk = false, lastNameOk = false;
		
		if(person.getFirstName() != null ) { firstNameOk = stringUtilsService.checkStringAddress(person.getFirstName()); 
		}
		if(person.getLastName() != null ) { lastNameOk = stringUtilsService.checkStringAddress(person.getLastName()); 
		}
		
		if (firstNameOk & lastNameOk ) {
			return true;
		} else {
			return false;
		}
	}

}
