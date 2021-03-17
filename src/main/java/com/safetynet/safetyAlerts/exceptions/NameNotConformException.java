package com.safetynet.safetyAlerts.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.safetynet.safetyAlerts.dao.PersonDaoImpl;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameNotConformException extends Exception {

	private static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	private static final long serialVersionUID = 6537093403090596706L;
	
	public NameNotConformException(String message) {
        super(message);
        logger.info("** Nom de recherche non conforme ! **");
    }
}
