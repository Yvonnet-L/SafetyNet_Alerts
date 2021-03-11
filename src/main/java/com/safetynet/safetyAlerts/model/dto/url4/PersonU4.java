package com.safetynet.safetyAlerts.model.dto.url4;

import com.safetynet.safetyAlerts.model.dto.url6.MedicalBackground;

public class PersonU4 {

	private String lastName;;
	private String phone;
	private int age;
	private MedicalBackground medicalBackground;

	public PersonU4() {
		super();
	}

	@Override
	public String toString() {
		return "PersonU4 [lastName=" + lastName + ", phone=" + phone + ", age=" + age + ", medicalBackground="
				+ medicalBackground + "]";
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public MedicalBackground getMedicalBackground() {
		return medicalBackground;
	}

	public void setMedicalBackground(MedicalBackground medicalBackground) {
		this.medicalBackground = medicalBackground;
	}

}
