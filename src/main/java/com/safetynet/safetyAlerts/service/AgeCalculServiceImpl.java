package com.safetynet.safetyAlerts.service;

import java.util.Date;

public class AgeCalculServiceImpl implements AgeCalculService {

	@Override
	public Integer personCalulateAge(Date dateOfBirthDay, int age) {

		Date today = new Date();
		Date fin = dateOfBirthDay;
		Date deb = today;

		long diff = deb.getTime() - fin.getTime();
		float nbannée = diff / 3600000.0f;
		nbannée = nbannée / (365 * 24);
		age = Math.round(nbannée);
		return age;
	}

}
