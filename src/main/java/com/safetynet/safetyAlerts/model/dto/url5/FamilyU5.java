package com.safetynet.safetyAlerts.model.dto.url5;

import java.util.List;

import com.safetynet.safetyAlerts.model.dto.url4.PersonU4;

public class FamilyU5 {

	private String address;
	private List<PersonU4> persons;

	public FamilyU5() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PersonU4> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonU4> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "FamilyU5 [address=" + address + ", persons=" + persons + "]";
	}
	
	
}
