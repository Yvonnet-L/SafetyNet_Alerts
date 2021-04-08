package com.safetynet.safetyAlerts.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNotConformException extends RuntimeException {

	private static Logger logger = LoggerFactory.getLogger(DataNotConformException.class);

	private static final long serialVersionUID = 6537093403090596706L;
	
	public DataNotConformException(String message) {
		super(message);        
        logger.error("--> " + message);
    }
}
