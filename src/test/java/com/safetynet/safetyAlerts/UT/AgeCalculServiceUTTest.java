package com.safetynet.safetyAlerts.UT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.safetynet.safetyAlerts.service.AgeCalculService;

public class AgeCalculServiceUTTest {

	
	@Test
	public void ageCalulServiceWithBirthDayLessTenYearsTest() {
		// GIVEN
		AgeCalculService ageCalcul = new AgeCalculService();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -10);
		Date birthDay = calendar.getTime();		
		int age = 0;
		// WHEN
		// THEN
		assertEquals(ageCalcul.personCalulateAge(birthDay, age), 10);
	}
	
	@Test
	public void ageCalulServiceWithBirthDayBeforeTodayTest() {
		// GIVEN
		AgeCalculService ageCalcul = new AgeCalculService();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, +10);
		Date birthDay = calendar.getTime();		
		int age = 0;
		// WHEN
		// THEN
		assertEquals(ageCalcul.personCalulateAge(birthDay, age), 0);
	}
	
	
}
