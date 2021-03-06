package com.safetynet.safetyAlerts.dao;

import java.util.List;

import com.safetynet.safetyAlerts.model.PersonModel;


public interface PersonDao {

	public List<PersonModel> findAll();
	
	public List<PersonModel> findById(String lastname);
	
	public PersonModel save(PersonModel Person);
	
	public List<PersonModel> delete(PersonModel Person);

	public void setAllPersons(List<PersonModel> listPerson);

	public PersonModel put(PersonModel person);

	/*public MailsByCity allMailOfCity(String city);

	PersonsCoveredByStationU1 allPersonCoveredByOneStation(String stationNb);

	public Collection<List<String>> allChildsByAdressWithParents(String adress);

	PersonsListU6 infoByPerson(String firstName, String lastName);

	ChildsWithParentsU2 allChildsByAdressWithParents2(String adress);

	public PhoneAlert PhoneNumbersForStation(String firestation_number);

	public PersonsListU4 PersonsByAdressWithStation(String address);

	public FamilysListU5 FamilystByAdressWithStation(String station);
    */
	

	
}
