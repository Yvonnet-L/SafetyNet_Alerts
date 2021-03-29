package com.safetynet.safetyAlerts.model.dto.url6;

public class PersonU6 {

	private String firstName;
	private String lastName;;
	private String phone;
	private int age;
	private String email;
	private MedicalBackground medicalBackground;

	public PersonU6() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MedicalBackground getMedicalBackground() {
		return medicalBackground;
	}

	public void setMedicalBackground(MedicalBackground medicalBackground) {
		this.medicalBackground = medicalBackground;
	}

	@Override
	public String toString() {
		return "PersonU6 [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", age=" + age
				+ ", email=" + email + ", medicalBackground=" + medicalBackground + "]";
	}

	
	
}
