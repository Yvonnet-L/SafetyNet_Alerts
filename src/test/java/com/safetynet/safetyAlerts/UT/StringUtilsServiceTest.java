package com.safetynet.safetyAlerts.UT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.safetynet.safetyAlerts.service.StringUtilsService;


public class StringUtilsServiceTest {

	private StringUtilsService stringUtilsService = new StringUtilsService();
	
	@Test
	public void StringNameOkTest() {
		String Name = "John";
		assertEquals(stringUtilsService.checkStringName(Name), true);
	}
	
	@Test
	public void StringNameEmptyTest() {
		String Name = "";
		assertEquals(stringUtilsService.checkStringName(Name), false);
	}
	
	

	@Test
	public void StringNameBlanckTest() {
		String Name = "";
		assertEquals(stringUtilsService.checkStringName(Name), false);
	}
	
	@Test
	public void StringNamefalseTest() {
		String Name = "John54";
		assertEquals(stringUtilsService.checkStringName(Name), false);
	}
	
	@Test
	public void StringNameWithParticuleTest() {	
		String Name = "Pierre-Allain";
		assertEquals(stringUtilsService.checkStringName(Name), true);
	}
		
	@Test
	public void StringAddressCorrectTest() {
		String Name = "1509 Culver St";
		assertEquals(stringUtilsService.checkStringAddress(Name), true);
	}
	
	@Test
	public void StringAddressCorrectWithPointTest() {
		String Name = "947 E. Rose Dr";
		assertEquals(stringUtilsService.checkStringAddress(Name), true);
	}
	
	@Test
	public void StringAddressCorrectWithApostrophTest() {
		String Name = "947 rue de l'enclave";
		assertEquals(stringUtilsService.checkStringAddress(Name), true);
	}
	
	@Test
	public void StringAddressNoCorrectTest() {
		String Name = "1509 Culver @ () St";
		assertEquals(stringUtilsService.checkStringAddress(Name), false);
	}
}
