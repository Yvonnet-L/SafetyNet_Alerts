package com.safetynet.safetyAlerts.model;

import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class DataModel {

	private List<PersonModel> persons;
	private List<FirestationModel> firestations;
	private List<MedicalrecordModel> medicalrecords;
	

	public List<PersonModel> getPersons() {
		return persons;
	}
	public void setPersons(List<PersonModel> persons) {
		this.persons = persons;
	}
	public List<FirestationModel> getFirestations() {
		return firestations;
	}
	public void setFirestations(List<FirestationModel> firestations) {
		this.firestations = firestations;
	}
	public List<MedicalrecordModel> getMedicalrecords() {
		return medicalrecords;
	}
	public void setMedicalrecords(List<MedicalrecordModel> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
		
}

