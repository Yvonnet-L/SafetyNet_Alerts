package com.safetynet.safetyAlerts.service;

import java.util.regex.Pattern;

public class StringUtilsService {

	public boolean checkStringName(String string) {
		
		Pattern stringNamePattern = Pattern.compile("[a-zA-Z\\+\\-\\+]{2,100}");		
		return stringNamePattern.matcher(string).matches();
	}
	
	
	public boolean checkStringAddress(String address) {
		
		Pattern stringAddressPattern = Pattern.compile("[a-zA-Z0-9 \\t\\n\\x0B\\f\\r+\\.\\+\\'\\+]{2,250}");		
		return stringAddressPattern.matcher(address).matches();
	}


	public boolean checkStringStation(String station) {
		Pattern stringNamePattern = Pattern.compile("[a-zA-Z0-9\\+\\-\\+]{1,60}");		
		return stringNamePattern.matcher(station).matches();
	}
	
	
}
