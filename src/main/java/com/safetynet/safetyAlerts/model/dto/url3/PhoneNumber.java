package com.safetynet.safetyAlerts.model.dto.url3;

public class PhoneNumber {

	private String number;


	@Override
	public String toString() {
		return "PhoneNumber [number=" + number + "]";
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
