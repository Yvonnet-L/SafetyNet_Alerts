package com.safetynet.safetyAlerts.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.safetynet.safetyAlerts.dao.PersonDaoImpl;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonIntrouvableException extends RuntimeException {
	
	private static Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	private static final long serialVersionUID = 6537093403090596706L;
	public PersonIntrouvableException(String message) {
        super(message);
        logger.info("--> Aucune personne trouvée !");
    }
	
}
