package com.safetynet.safetyAlerts.model.dto.url6;

import java.util.List;

public class MedicalBackground {
	
	private List<String> medications;
	private List<String> allergies;

	public MedicalBackground() {
		super();

	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	@Override
	public String toString() {
		return "MedicalBackground [medications=" + medications + ", allergies=" + allergies + "]";
	}
	
	
}
