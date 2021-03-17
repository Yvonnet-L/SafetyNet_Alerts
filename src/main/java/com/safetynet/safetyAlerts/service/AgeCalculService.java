package com.safetynet.safetyAlerts.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class AgeCalculService {

	public Integer personCalulateAge(Date birthDay, int age) {

		Date today = new Date();
		Date fin = birthDay;
		Date deb = today;
		if (birthDay.before(today)) {
			long diff = deb.getTime() - fin.getTime();
			float nbannée = diff / 3600000.0f;
			nbannée = nbannée / (365 * 24);
			age = Math.round(nbannée);		
		}else {
			age=0;
		}
		return age;
	}

}
