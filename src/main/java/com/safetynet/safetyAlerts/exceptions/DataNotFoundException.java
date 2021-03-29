package com.safetynet.safetyAlerts.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

	private static Logger logger = LoggerFactory.getLogger(DataNotFoundException.class);
	
	private static final long serialVersionUID = -3988552783621921445L;

	public DataNotFoundException(String message) {
        super(message);
        logger.error("--> " + message);
    }
}
