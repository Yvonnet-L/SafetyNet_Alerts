package com.safetynet.safetyAlerts.model.dto.url5;

import java.util.List;

public class FamilysListU5 {

	private String fireStation;
	private List<FamilyU5> personsListU5;

	public FamilysListU5() {
		super();
	}

	public String getFireStation() {
		return fireStation;
	}

	public void setFireStation(String fireStation) {
		this.fireStation = fireStation;
	}

	public List<FamilyU5> getPersonsListU5() {
		return personsListU5;
	}

	public void setPersonsListU5(List<FamilyU5> personsListU5) {
		this.personsListU5 = personsListU5;
	}
}
