package com.safetynet.safetyAlerts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalRecordIntrouvableException extends RuntimeException {

	private static final long serialVersionUID = -3988552783621921445L;
	
	public MedicalRecordIntrouvableException() {
        this("MedicalReciord not found");
    }

	public MedicalRecordIntrouvableException(String message) {
        super(message);
    }
}
