package com.safetynet.safetyAlerts.dao;

import java.util.List;

import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;


public interface PersonDao {

	public List<PersonModel> findAll();
	
	public List<PersonModel> findById(String lastname);
	
	public PersonModel save(PersonModel Person);
	
	public List<PersonModel> delete(PersonModel Person);

	public void setAllPersons(List<PersonModel> listPerson);

	public PersonModel put(PersonModel person);

	public void setAllMedicalrecords(List<MedicalrecordModel> listMedicalRecord);

	public void setAllFireStations(List<FirestationModel> listFireStation);

	void upDateData();

}
