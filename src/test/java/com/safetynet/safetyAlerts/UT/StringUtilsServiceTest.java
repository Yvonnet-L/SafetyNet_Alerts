package com.safetynet.safetyAlerts.UT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.safetynet.safetyAlerts.service.StringUtilsService;


public class StringUtilsServiceTest {

	private StringUtilsService stringUtilsService = new StringUtilsService();
	
	
	@Nested
	class StringNameTest {
		
		@Test
		@DisplayName("Test avec un nom conform")
		public void StringNameOkTest() {
			String Name = "John";
			assertEquals(stringUtilsService.checkStringName(Name), true);
		}
		
		@Test
		@DisplayName("Test avec un nom vide")
		public void StringNameEmptyTest() {
			String Name = "";
			assertEquals(stringUtilsService.checkStringName(Name), false);
		}
		
		
	
		@Test
		@DisplayName("Test avec un nom blanc")
		public void StringNameBlanckTest() {
			String Name = "";
			assertEquals(stringUtilsService.checkStringName(Name), false);
		}
		
		@Test
		@DisplayName("Test avec un nom non-conform")
		public void StringNamefalseTest() {
			String Name = "John54";
			assertEquals(stringUtilsService.checkStringName(Name), false);
		}
		
		@Test
		@DisplayName("Test avec un nom avec particule -")
		public void StringNameWithParticuleTest() {	
			String Name = "Pierre-Allain";
			assertEquals(stringUtilsService.checkStringName(Name), true);
		}
	}
		
	@Nested
	class StringAddressTest {
	
		@Test
		@DisplayName("Test avec une adresse correcte")
		public void StringAddressCorrectTest() {
			String Name = "1509 Culver St";
			assertEquals(stringUtilsService.checkStringAddress(Name), true);
		}
		
		@Test
		@DisplayName("Test avec une adresse conforme avec un point.")
		public void StringAddressCorrectWithPointTest() {
			String Name = "947 E. Rose Dr";
			assertEquals(stringUtilsService.checkStringAddress(Name), true);
		}
		
		@Test
		@DisplayName("Test avec une adresse correcte avec une apostrophe '")
		public void StringAddressCorrectWithApostrophTest() {
			String Name = "947 rue de l'enclave";
			assertEquals(stringUtilsService.checkStringAddress(Name), true);
		}
		
		@Test
		@DisplayName("Test avec une adresse non-conforme")
		public void StringAddressNoCorrectTest() {
			String Name = "1509 Culver @ () St";
			assertEquals(stringUtilsService.checkStringAddress(Name), false);
		}
	}
}
