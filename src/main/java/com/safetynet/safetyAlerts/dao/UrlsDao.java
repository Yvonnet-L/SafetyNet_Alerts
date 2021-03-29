package com.safetynet.safetyAlerts.dao;

import java.util.List;

import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.model.dto.url1.PersonsCoveredByStationU1;
import com.safetynet.safetyAlerts.model.dto.url2.ChildsWithParentsU2;
import com.safetynet.safetyAlerts.model.dto.url3.PhoneAlertU3;
import com.safetynet.safetyAlerts.model.dto.url4.PersonsListU4;
import com.safetynet.safetyAlerts.model.dto.url5.FamilysListU5;
import com.safetynet.safetyAlerts.model.dto.url6.PersonsListU6;
import com.safetynet.safetyAlerts.model.dto.url7.MailsByCity;

public interface UrlsDao {

	public PersonsCoveredByStationU1 allPersonCoveredByOneStation(String stationNumber);
	
	public ChildsWithParentsU2 allChildsByAdressWithParents(String adress);

	public PhoneAlertU3 phoneNumbersForStation(String firestationNumber);

	public PersonsListU4 personsByAdressWithStation(String address);

	public FamilysListU5 familystByAdressWithStation(String station);

	public PersonsListU6 infoByPerson(String firstName, String lastName);

	public MailsByCity allMailOfCity(String city);

	public void setAllFireStations(List<FirestationModel> listFireStation);
	
	public void setAllPersons(List<PersonModel> listPerson);



}
