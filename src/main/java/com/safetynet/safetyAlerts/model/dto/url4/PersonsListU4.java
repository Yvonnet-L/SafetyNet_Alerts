package com.safetynet.safetyAlerts.model.dto.url4;

import java.util.List;

public class PersonsListU4 {

	private String address;
	private String fireStation;
	private List<PersonU4> persons;

	public PersonsListU4() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFireStation() {
		return fireStation;
	}

	public void setFireStation(String fireStation) {
		this.fireStation = fireStation;
	}

	public List<PersonU4> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonU4> persons) {
		this.persons = persons;
	}
}
