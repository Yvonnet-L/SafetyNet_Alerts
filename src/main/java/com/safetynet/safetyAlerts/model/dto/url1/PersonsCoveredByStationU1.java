package com.safetynet.safetyAlerts.model.dto.url1;

import java.util.List;

public class PersonsCoveredByStationU1 {

	private int nbAdult;
	private int nbChild;
	private List<PersonStation> persons;


	public int getNbAdult() {
		return nbAdult;
	}

	public void setNbAdult(int nbAdult) {
		this.nbAdult = nbAdult;
	}

	public int getNbChild() {
		return nbChild;
	}

	public void setNbChild(int nbChild) {
		this.nbChild = nbChild;
	}

	public List<PersonStation> getPerson() {
		return persons;
	}

	public void setPerson(List<PersonStation> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "PersonsCoveredByStationU1 [nbAdult=" + nbAdult + ", nbChild=" + nbChild + ", persons=" + persons.toString() + "]";
	}

	

}
