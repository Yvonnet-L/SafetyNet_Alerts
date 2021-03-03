package com.safetynet.safetyAlerts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PersonModel {

	
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;
	private String firestation;
	@JsonIgnore
	private int age;
	@JsonIgnore
	private MedicalrecordModel medicalrecord;
	
	public PersonModel() {
	}

	public PersonModel(Long id, String firstName, String lastName, String address, String city, int zip, String phone,
			String email, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.age = age;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirestation() {
		return firestation;
	}

	public void setFirestation(String firestation) {
		this.firestation = firestation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public MedicalrecordModel getMedicalrecord() {
		return medicalrecord;
	}

	public void setMedicalrecord(MedicalrecordModel medicalrecord) {
		this.medicalrecord = medicalrecord;
	}

	@Override
	public String toString() {
		return "{ \"firstName\":\"" + firstName + "\", \"lastName\":\"" + lastName + "\", \"address\":\"" + address + "\", \"city\":\""
				+ city + "\", \"zip\":\"" + zip + "\", \"phone\":\"" + phone + "\", \"email\":\"" + email + "\", \"firestation\":\"" + firestation
				+ "\", \"age\":\"" + age + "\", \"medicalrecord\":\"" + medicalrecord + "]";
	}

	public String toStringDto() {
		return "{ \"firstName\":\"" + firstName + "\", \"lastName\":\"" + lastName + "\", \"address\":\"" + address + "\", \"city\":\""
				+ city + "\", \"zip\":\"" + zip + "\", \"phone\":\"" + phone + "]";
	}

	/*public String toString() {
	return "{ \"address\":\"" + address + "\", \"station\":\"" + station  + "\"}";
}*/

}




