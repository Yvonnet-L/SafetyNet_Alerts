package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.model.PersonModel;



@Repository
public class PersonDaoImpl implements PersonDao {

	private static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	public List<PersonModel> persons = new ArrayList<>();

	@Override
	public void setAllPersons(List<PersonModel> listPerson) {
		this.persons = listPerson;

	}

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
		if(personFind.size()>0) {
			logger.info("--> Liste des Personnes trouvées pour {}: {}", lastName, personFind);
			return personFind;
		}else {
			logger.info("--> Aucune personne trouvée pour {}", lastName);
			return null;
		}
	}

	@Override
	public PersonModel save(PersonModel person) {
		
		PersonModel personSelect = null;
		for (PersonModel p : persons) {
			if (p.getFirstName().equals(person.getFirstName())
					& p.getLastName().equals(person.getLastName())) {
				personSelect = p;
			}
		}
		if (personSelect == null) {
			persons.add(person);
			logger.info("-->  Personne crée : {}", person);
			return person;
		} else {
			logger.info("*** Création impossible une personne pour {} {} est déjà existante !", person.getFirstName(),
					person.getLastName());
			return null;
		}
	}

	@Override
	public List<PersonModel> delete(PersonModel person) {

		List<PersonModel> personSelect = new ArrayList<PersonModel>();
		for (PersonModel p : persons) {
			if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
				personSelect.add(p);
			}
		}
		persons.removeAll(personSelect);
		if (personSelect.isEmpty()) {
			logger.info("--> Aucune Personne trouvée à ce nom {} {} !", person.getFirstName(), person.getLastName());
		} else
			logger.info("--> Listes des Personnes Supprimées pour {} {}: {}",
												person.getFirstName(), person.getLastName(), personSelect);
			return personSelect;
	}

	@Override
	public PersonModel put(PersonModel person) {
		PersonModel personSelect = new PersonModel();
			for (PersonModel p : persons) {
				if (p.getFirstName().equals(person.getFirstName()) & p.getLastName().equals(person.getLastName())) {
					person.setMedicalrecord(p.getMedicalrecord());
					persons.set(persons.indexOf(p), person);
					personSelect = p;
				}
		}
		logger.info("Personne Modifiée: {} par {}", personSelect, person);
		return person;
	}
/*
	@Override
	public MailsByCity allMailOfCity(String city) {
		List<String> mailsList = new ArrayList<>();
		MailsByCity mailsByCity = new MailsByCity();
		Set<String> hsetMails = new HashSet<>();
		for (PersonModel p : persons) {
			if (p.getCity().equals(city)) {
				//mailsList.add(p.getEmail());
				hsetMails.add(p.getEmail());
			}
		}
		logger.info("Liste des mails de toutes les Personnes de la ville {} : {}", city, mailsList);
		mailsByCity.setCity(city);
		//mailsByCity.setMails(mailsList);
		mailsByCity.setMails(hsetMails);
		return mailsByCity;

	}

	@Override
	public PersonsCoveredByStationU1 allPersonCoveredByOneStation(String stationNb) {
		List<PersonStation> personsStation = new ArrayList<>();
		
		PersonsCoveredByStationU1 personsCoveredByStation = new PersonsCoveredByStationU1();
		int nbAdult = 0;
		int nbChild = 0;
		for (PersonModel p : persons) {
			if (p.getFirestation().equals(stationNb)) {
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
		personsCoveredByStation.setPerson(personsStation);
		personsCoveredByStation.setNbAdult(nbAdult);
		personsCoveredByStation.setNbChild(nbChild);
		logger.info("Objet PersonsCoveredByStation {} : nbAdult:{}  nbChild:{} ListPersons:{}", stationNb, nbChild,
				nbAdult, personsStation);
		return personsCoveredByStation;
	}

	@Override
	public Collection<List<String>> allChildsByAdressWithParents(String adress) {

		Collection<List<String>> personsGroupFind = new ArrayList<>();
		List<String> childsFind = new ArrayList<>();
		List<String> parentsFind = new ArrayList<>();
		boolean i = false, j = false;

		for (PersonModel p : persons) {
			if (p.getAddress().equals(adress)) {
				if (p.getAge() > 18) {
					if (i == false) {
						parentsFind.add("Parent: ");
						i = true;
					}
					parentsFind.add("  - " + p.getFirstName() + " " + p.getLastName());

				} else {
					if (j == false) {
						childsFind.add("Enfant: ");
						j = true;
					}
					childsFind.add("  - " + p.getFirstName() + " " + p.getLastName() + " , " + p.getAge() + " ans");
				}
			}
		}
		personsGroupFind.add(childsFind);
		personsGroupFind.add(parentsFind);
		logger.info("Liste des Personnes trouvées par {}: {}", adress, personsGroupFind);
		return personsGroupFind;
	}

	@Override
	public ChildsWithParentsU2 allChildsByAdressWithParents2(String adress) {

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
		logger.info("Liste des Personnes trouvées par {}: {}", adress, personsGroupFind);
		return personsGroupFind;
	}

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
		logger.info("Liste des Personnes trouvées par {} {}:  {}", firstName, lastName, infoPersons);
		return infoPersons;

	}

	@Override
	public PhoneAlert PhoneNumbersForStation(String firestation_number) {
		PhoneAlert phoneAlert = new PhoneAlert();
		List<PhoneNumber> phoneList = new ArrayList<>();
		PhoneNumber phoneNumber = new PhoneNumber();
		for (PersonModel p : persons) {
			if (p.getFirestation().equals(firestation_number)) {
				phoneNumber.setNumber(p.getPhone());
				logger.info("phone find for station n°{}: {}", firestation_number, p.getPhone());
				if (phoneNumber != null) {
					phoneList.add(phoneNumber);
				}
			}
		}
		phoneAlert.setPhoneList(phoneList);
		return phoneAlert;
	}
	
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
		logger.info("Person find for address / station n°{}: {}", address, personU4List);
		return personsU4;
	}

	@Override
	public FamilysListU5 FamilystByAdressWithStation(String station) {
		FamilysListU5 familysListU5 = new FamilysListU5();
		
		List<FamilyU5> familyList = new ArrayList<>();
		List<PersonU4> personsList = new ArrayList<>();
		FamilyU5 familyU5 = null;
		MedicalBackground medicalBackground;
		PersonU4 personU4;
		Set<String> hsetAddress = new HashSet<>();		
		logger.info("Person find for address / station n°{}:" , station);
		
		for (PersonModel p : persons) {
			if (p.getFirestation().equals(station)) {
				hsetAddress.add(p.getAddress());
				logger.info("Person find for address / station n°{}:" , hsetAddress.iterator());
			}			
		}
		logger.info("nunber of address find by station: {} address:" , hsetAddress.size());
		for(String s : hsetAddress) {
			logger.info("address: {}" , s);	
			familyU5 = new FamilyU5();
			
			for (PersonModel p: persons) {
				
				if (p.getAddress().equals(s)) {
					personU4 = new PersonU4();				
					medicalBackground = new MedicalBackground();
					personU4.setLastName(p.getLastName());
					personU4.setPhone(p.getPhone());
					personU4.setAge(p.getAge());

					medicalBackground.setAllergies(p.getMedicalrecord().getAllergies());
					medicalBackground.setMedications(p.getMedicalrecord().getMedications());
					personU4.setMedicalBackground(medicalBackground);	
					
					logger.info("person: {}" , personU4);
					personsList.add(personU4);
				}			
			}
			familyU5.setAddress(s);
			familyU5.setPersons(personsList);
			familyList.add(familyU5);					
		}
		familysListU5.setFireStation(station);
		familysListU5.setPersonsListU5(familyList);
		return familysListU5;

	}
*/
}
