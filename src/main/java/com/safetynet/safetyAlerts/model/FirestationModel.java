package com.safetynet.safetyAlerts.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class FirestationModel {
	

	private String station;
	private String address;
	@JsonIgnore
	private int nbPerson;
	@JsonIgnore 
	private List<PersonModel> person;
	
		
	public FirestationModel() {
	}

	public FirestationModel(String station, String address) {
		this.station = station;
		this.address = address;
	}
		
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public List<PersonModel> getPerson() {
		return person;
	}

	public void setPerson(List<PersonModel> person) {
		this.person = person;
	}
	
	public int getNbPerson() {
		return nbPerson;
	}

	public void setNbPerson(int nbPerson) {
		this.nbPerson = nbPerson;
	}


	@Override
	public String toString() {
		return "FirestationModel [station=" + station + ", address=" + address + "]";
	}

	
	
}



