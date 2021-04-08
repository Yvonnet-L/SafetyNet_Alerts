package com.safetynet.safetyAlerts.UT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.safetynet.safetyAlerts.service.AgeCalculService;

public class AgeCalculServiceUTTest {

	
	@Test
	@DisplayName("Test du calcul de l'age pour Date moins 10 ans")
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
	@DisplayName("Test du calcul de l'age pour Date posterieur +10 ans")
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
	
	
	@Test
	@DisplayName("Test du calcul de l'age pour Date = Date du jour")
	public void ageCalulServiceWithBirthDayTodayTest() {
		// GIVEN
		AgeCalculService ageCalcul = new AgeCalculService();
		Calendar calendar = Calendar.getInstance();
		Date birthDay = calendar.getTime();		
		int age = 0;
		// WHEN
		// THEN
		assertEquals(ageCalcul.personCalulateAge(birthDay, age), 0);
	}
}
