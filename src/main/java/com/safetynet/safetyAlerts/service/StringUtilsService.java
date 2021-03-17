package com.safetynet.safetyAlerts.service;

import java.util.regex.Pattern;

public class StringUtilsService {

	public boolean checkStringName(String string) {
		
		Pattern stringNamePattern = Pattern.compile("[a-zA-Z\\+\\-\\+]{1,250}");		
		return stringNamePattern.matcher(string).matches();
	}
	
	
	public boolean checkStringAddress(String string) {
		
		Pattern stringAddressPattern = Pattern.compile("[a-zA-Z0-9 \\t\\n\\x0B\\f\\r+\\.\\+\\'\\+]{1,250}");		
		return stringAddressPattern.matcher(string).matches();
	}
	
	
}
