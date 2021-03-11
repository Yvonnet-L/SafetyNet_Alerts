package com.safetynet.safetyAlerts.dao;

import java.util.List;

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

	public void setAllPersons(List<PersonModel> listPerson);

	public PhoneAlertU3 PhoneNumbersForStation(String firestationNumber);

	public PersonsListU4 PersonsByAdressWithStation(String address);

	public FamilysListU5 FamilystByAdressWithStation(String station);

	public PersonsListU6 infoByPerson(String firstName, String lastName);

	public MailsByCity allMailOfCity(String city);



}
