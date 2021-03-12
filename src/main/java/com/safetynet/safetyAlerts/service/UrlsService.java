package com.safetynet.safetyAlerts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.UrlsDao;
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

	@Autowired
	private UrlsDao urlsDao;

	public PersonsCoveredByStationU1 allPersonCoveredByOneStation(String stationNumber) {
		if (stationNumber.isEmpty() || stationNumber.isBlank()) {
			return null;
		} else {
			logger.info("Lancement de la recherche des Personnes rattachées à la fireStation: {}", stationNumber);
			return urlsDao.allPersonCoveredByOneStation(stationNumber);
		}
	}

	public ChildsWithParentsU2 allChildsByAdressWithParents(String address) {
		if (address.isBlank() || address.isEmpty()) {
			return null;
		} else {
			logger.info("Lancement de la recherche des enfants rattachées à l'adresse: {}", address);
			return urlsDao.allChildsByAdressWithParents(address);
		}
	}

	public PhoneAlertU3 PhoneNumbersForStation(String firestationNumber) {
		if (firestationNumber.isBlank() || firestationNumber.isEmpty()) {
			return null;
		} else {
			logger.info("Lancement de la recherche des enfants rattachées à l'adresse: {}", firestationNumber);
			return urlsDao.PhoneNumbersForStation(firestationNumber);
		}
	}

	public PersonsListU4 PersonsByAdressWithStation(String address) {
		if (address.isBlank() || address.isEmpty()) {
			return null;
		} else {
			logger.info("Lancement de la recherche des personnes rattachées l'adresse: {}", address);
			return urlsDao.PersonsByAdressWithStation(address);
		}
	}

	public FamilysListU5 FamilystByAdressWithStation(String station) {
		if (station.isBlank() || station.isEmpty()) {
			return null;
		} else {
			logger.info("Lancement de la recherche des familles rattachées la station: {}", station);
			return urlsDao.FamilystByAdressWithStation(station);
		}
	}

	public PersonsListU6 infoByPerson(String firstName, String lastName) {
		if (firstName.isBlank() || firstName.isEmpty() || lastName.isBlank() || lastName.isEmpty()) {
			return null;
		} else {
		logger.info("Lancement de la recherche des personnes: {} {}", firstName, lastName);
		return urlsDao.infoByPerson(firstName, lastName);
		}
	}

	public MailsByCity allMailOfCity(String city) {
		if (city.isBlank() || city.isEmpty()) {
			return null;
		} else 
		logger.info("Lancement de la recherche des mails pour la ville: {}", city);
		return urlsDao.allMailOfCity(city);
	}

}
