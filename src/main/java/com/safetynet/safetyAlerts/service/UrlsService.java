package com.safetynet.safetyAlerts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.UrlsDao;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.dto.url1.PersonsCoveredByStationU1;
import com.safetynet.safetyAlerts.model.dto.url2.ChildsWithParentsU2;
import com.safetynet.safetyAlerts.model.dto.url3.PhoneAlertU3;
import com.safetynet.safetyAlerts.model.dto.url4.PersonsListU4;
import com.safetynet.safetyAlerts.model.dto.url5.FamilysListU5;
import com.safetynet.safetyAlerts.model.dto.url6.PersonsListU6;
import com.safetynet.safetyAlerts.model.dto.url7.MailsByCity;

@Service
public class UrlsService {

	private static Logger logger = LoggerFactory.getLogger(UrlsService.class);
	
	StringUtilsService stringValitaded = new StringUtilsService();

	@Autowired
	private UrlsDao urlsDao;
	
	//--------------------------------------------------------------------------------------------------------
	public PersonsCoveredByStationU1 allPersonCoveredByOneStation(String stationNumber) {
		
		if (stringValitaded.checkStringStation(stationNumber)) {
			logger.info("Lancement de la recherche des Personnes rattachées à la fireStation: {}", stationNumber);
			return urlsDao.allPersonCoveredByOneStation(stationNumber);
		} else throw new DataNotConformException("*** Recherche avortée, nom de station non conforme !");
	}
	
	//--------------------------------------------------------------------------------------------------------
	public ChildsWithParentsU2 allChildsByAdressWithParents(String address) {
		
		if (stringValitaded.checkStringAddress(address)) {
			logger.info("Lancement de la recherche des enfants rattachées à l'adresse: {}", address);
			return urlsDao.allChildsByAdressWithParents(address);
		} else throw new DataNotConformException("*** Recherche avortée, adresse non conforme !");
	}

	//--------------------------------------------------------------------------------------------------------
	public PhoneAlertU3 PhoneNumbersForStation(String stationNumber) {
		
		if (stringValitaded.checkStringStation(stationNumber)) {
			logger.info("Lancement de la recherche des enfants rattachées à l'adresse: {}", stationNumber);
			return urlsDao.PhoneNumbersForStation(stationNumber);
		} else throw new DataNotConformException("*** Recherche avortée, nom de station non conforme !");
	}

	//--------------------------------------------------------------------------------------------------------
	public PersonsListU4 PersonsByAdressWithStation(String address) {
		
		if (stringValitaded.checkStringAddress(address)) {
			logger.info("Lancement de la recherche des personnes rattachées l'adresse: {}", address);
			return urlsDao.PersonsByAdressWithStation(address);
		} else throw new DataNotConformException("*** Recherche avortée, adresse non conforme !");
	}

	//--------------------------------------------------------------------------------------------------------
	public FamilysListU5 FamilystByAdressWithStation(String stationNumber) {
		
		if (stringValitaded.checkStringStation(stationNumber)) {
			logger.info("Lancement de la recherche des familles rattachées la station: {}", stationNumber);
			return urlsDao.FamilystByAdressWithStation(stationNumber);
		}else throw new DataNotConformException("*** Recherche avortée, nom de station non conforme !");
	}

	//--------------------------------------------------------------------------------------------------------
	public PersonsListU6 infoByPerson(String firstName, String lastName) {
		
		if ((stringValitaded.checkStringName(firstName)) & (stringValitaded.checkStringName(lastName))) {
			logger.info("Lancement de la recherche des personnes: {} {}", firstName, lastName);
			return urlsDao.infoByPerson(firstName, lastName);
		}else throw new DataNotConformException("*** Recherche avortée, nom ou prénom non conforme !");
	}

	//--------------------------------------------------------------------------------------------------------
	public MailsByCity allMailOfCity(String city) {
		if (stringValitaded.checkStringName(city)) {
			logger.info("Lancement de la recherche des mails pour la ville: {}", city);
			return urlsDao.allMailOfCity(city);
		}else throw new DataNotConformException("*** Recherche avortée, nom de city non conforme !");}

}
