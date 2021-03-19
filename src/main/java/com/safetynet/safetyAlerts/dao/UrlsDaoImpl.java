package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.model.dto.url1.PersonStation;
import com.safetynet.safetyAlerts.model.dto.url1.PersonsCoveredByStationU1;
import com.safetynet.safetyAlerts.model.dto.url2.ChildsWithParentsU2;
import com.safetynet.safetyAlerts.model.dto.url2.PersonWithAgeU2;
import com.safetynet.safetyAlerts.model.dto.url3.PhoneAlertU3;
import com.safetynet.safetyAlerts.model.dto.url3.PhoneNumber;
import com.safetynet.safetyAlerts.model.dto.url4.PersonU4;
import com.safetynet.safetyAlerts.model.dto.url4.PersonsListU4;
import com.safetynet.safetyAlerts.model.dto.url5.FamilyU5;
import com.safetynet.safetyAlerts.model.dto.url5.FamilysListU5;
import com.safetynet.safetyAlerts.model.dto.url6.MedicalBackground;
import com.safetynet.safetyAlerts.model.dto.url6.PersonU6;
import com.safetynet.safetyAlerts.model.dto.url6.PersonsListU6;
import com.safetynet.safetyAlerts.model.dto.url7.MailsByCity;
import com.safetynet.safetyAlerts.service.StringUtilsService;

@Repository
public class UrlsDaoImpl implements UrlsDao {

	private static Logger logger = LoggerFactory.getLogger(UrlsDaoImpl.class);

	public List<PersonModel> persons = new ArrayList<>();

	@Override
	public void setAllPersons(List<PersonModel> listPerson) {
		this.persons = listPerson;
	}

	// ----------------> Url - 1 <----------------------------------------
	@Override
	public PersonsCoveredByStationU1 allPersonCoveredByOneStation(String stationNumber) {
		
		StringUtilsService stringUtilsService = new StringUtilsService();
		boolean nameStationBoolean = stringUtilsService.checkStringAddress(stationNumber);
		logger.info("Résultat de la verification de la string {} est {}", stationNumber, nameStationBoolean);
		
		if (stationNumber.isBlank()) {
			return null;
		} else {
			List<PersonStation> personsStation = new ArrayList<>();

			PersonsCoveredByStationU1 personsCoveredByStation = new PersonsCoveredByStationU1();
			int nbAdult = 0;
			int nbChild = 0;
			for (PersonModel p : persons) {
				if(p.getFirestation() != null) {
					if (p.getFirestation().equals(stationNumber)) {
						PersonStation personStation = new PersonStation(p.getFirstName(), p.getLastName(), p.getAddress(),
								p.getCity(), p.getZip(), p.getPhone());
						personsStation.add(personStation);
						if (p.getAge() > 18) {
							nbAdult++;
						} else {
							nbChild++;
						}
					}
				}
			}
			personsCoveredByStation.setPerson(personsStation);
			personsCoveredByStation.setNbAdult(nbAdult);
			personsCoveredByStation.setNbChild(nbChild);
			if (nbAdult > 0 || nbChild > 0) {
				logger.info("--> Personness couvertent par la FireStation {} : nbAdult:{}  nbChild:{} ListPersons:{}",
						stationNumber, nbChild, nbAdult, personsStation);
			} else {
				logger.info("--> Aucune personne couverte par la FireStation {}", stationNumber);
			}
			return personsCoveredByStation;
		}

	}

	// ----------------> Url - 2 <----------------------------------------
	@Override
	public ChildsWithParentsU2 allChildsByAdressWithParents(String adress) {

		ChildsWithParentsU2 personsGroupFind = new ChildsWithParentsU2();
		List<PersonWithAgeU2> childs = new ArrayList<>();
		List<PersonWithAgeU2> parents = new ArrayList<>();

		for (PersonModel p : persons) {

			if (p.getAddress().equals(adress)) {
				PersonWithAgeU2 person = new PersonWithAgeU2(p.getFirstName(), p.getLastName(), p.getAge());
				if (p.getAge() > 18) {
					parents.add(person);
				} else
					childs.add(person);
			}
		}
		personsGroupFind.setEnfants(childs);
		personsGroupFind.setParents(parents);
		logger.info("--> Liste des Personnes trouvées par {}: {}", adress, personsGroupFind);
		return personsGroupFind;
	}

	// ----------------> Url - 3 <----------------------------------------
	@Override
	public PhoneAlertU3 PhoneNumbersForStation(String firestationNumber) {
	
			PhoneAlertU3 phoneAlert = new PhoneAlertU3();
			List<PhoneNumber> phoneList = new ArrayList<>();

			for (PersonModel p : persons) {
				if(p.getFirestation()!=null) {
					if (p.getFirestation().equals(firestationNumber)) {
						PhoneNumber phoneNumber = new PhoneNumber();
						phoneNumber.setNumber(p.getPhone());
						if (phoneNumber != null) {
							phoneList.add(phoneNumber);
						}
					}
				}
			}
			phoneAlert.setPhoneList(phoneList);
			logger.info("--> Téléphone(s) trouvé(s) par station n°{}: {}", firestationNumber, phoneList);
			return phoneAlert;	
	}

	// ----------------> Url - 4 <----------------------------------------
	@Override
	public PersonsListU4 PersonsByAdressWithStation(String address) {

		PersonsListU4 personsU4 = new PersonsListU4();
		List<PersonU4> personU4List = new ArrayList<>();
		PersonU4 personU4;
		MedicalBackground medicalBackground;
		String fireStation = null;
		for (PersonModel p : persons) {
			if (p.getAddress().equals(address)) {
				personU4 = new PersonU4();
				medicalBackground = new MedicalBackground();

				personU4.setLastName(p.getLastName());
				personU4.setPhone(p.getPhone());
				personU4.setAge(p.getAge());

				medicalBackground.setAllergies(p.getMedicalrecord().getAllergies());
				medicalBackground.setMedications(p.getMedicalrecord().getMedications());
				personU4.setMedicalBackground(medicalBackground);
				fireStation = p.getFirestation();

				personU4List.add(personU4);
			}
		}
		personsU4.setAddress(address);
		personsU4.setFireStation(fireStation);
		personsU4.setPersons(personU4List);
		logger.info("--> Personnes trouvées pour l'adress  n°{}: {}", address, personU4List);
		return personsU4;
	}

	// ----------------> Url - 5 <----------------------------------------
	@Override
	public FamilysListU5 FamilystByAdressWithStation(String station) {
	
			FamilysListU5 familysListU5 = new FamilysListU5();
			List<FamilyU5> familyList = new ArrayList<>();
			List<PersonU4> personsList = new ArrayList<>();
			FamilyU5 familyU5 = null;
			MedicalBackground medicalBackground;
			PersonU4 personU4;
			Set<String> hsetAddress = new HashSet<>();

			for (PersonModel p : persons) {
				if(p.getFirestation()!=null) {
					if (p.getFirestation().equals(station)) {
						hsetAddress.add(p.getAddress());
					}
				}
			}
			logger.info("nunber of address find by station: {} address:", hsetAddress.size());
			for (String s : hsetAddress) {
				familyU5 = new FamilyU5();

				for (PersonModel p : persons) {

					if (p.getAddress().equals(s)) {
						personU4 = new PersonU4();
						medicalBackground = new MedicalBackground();
						personU4.setLastName(p.getLastName());
						personU4.setPhone(p.getPhone());
						personU4.setAge(p.getAge());

						medicalBackground.setAllergies(p.getMedicalrecord().getAllergies());
						medicalBackground.setMedications(p.getMedicalrecord().getMedications());
						personU4.setMedicalBackground(medicalBackground);

						personsList.add(personU4);
					}
				}
				familyU5.setAddress(s);
				familyU5.setPersons(personsList);
				familyList.add(familyU5);
			}
			familysListU5.setFireStation(station);
			familysListU5.setPersonsListU5(familyList);
			logger.info("--> Listes des personnes trouvées: {}", familysListU5);
			return familysListU5;
	}

	// ----------------> Url - 6 <----------------------------------------
	@Override
	public PersonsListU6 infoByPerson(String firstName, String lastName) {

		PersonsListU6 infoPersons = new PersonsListU6();
		List<PersonU6> infoPersonList = new ArrayList<>();
		MedicalBackground medicalBackground = new MedicalBackground();
		PersonU6 personU6;

		for (PersonModel p : persons) {
			if (p.getFirstName().equals(firstName) & p.getLastName().equals(lastName)) {
				personU6 = new PersonU6();
				personU6.setFirstName(firstName);
				personU6.setLastName(lastName);
				personU6.setAge(p.getAge());
				personU6.setEmail(p.getEmail());
				personU6.setPhone(p.getPhone());
				if (p.getMedicalrecord() != null) {
					medicalBackground.setAllergies(p.getMedicalrecord().getAllergies());
					medicalBackground.setMedications(p.getMedicalrecord().getMedications());
				}
				personU6.setMedicalBackground(medicalBackground);

				infoPersonList.add(personU6);
			}
		}
		infoPersons.setPersons(infoPersonList);
		logger.info("--> Liste des Personnes trouvées par {} {}:  {}", firstName, lastName, infoPersons);
		return infoPersons;
	}

	// ----------------> Url - 7 <----------------------------------------
	@Override
	public MailsByCity allMailOfCity(String city) {

		MailsByCity mailsByCity = new MailsByCity();
		Set<String> hsetMails = new HashSet<>();
		for (PersonModel p : persons) {
			if (p.getCity().equals(city)) {
				hsetMails.add(p.getEmail());
			}
		}
		logger.info("--> Liste des mails de toutes les Personnes de la ville {} : {}", city, hsetMails);
		mailsByCity.setCity(city);
		mailsByCity.setMails(hsetMails);
		return mailsByCity;
	}

}
