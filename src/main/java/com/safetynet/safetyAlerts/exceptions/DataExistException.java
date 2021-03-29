package com.safetynet.safetyAlerts.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)	
public class DataExistException extends RuntimeException{
	
	private static Logger logger = LoggerFactory.getLogger(DataExistException.class);

	private static final long serialVersionUID = -74683833633613410L; 

	public DataExistException(String message) {
        super(message);
        logger.error("--> " + message);
    }
}
