package com.safetynet.safetyAlerts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.MedicalRecordDao;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;

@Service
public class MedicalRecordService {
	
	private static Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);

	StringUtilsService stringUtilsService = new StringUtilsService();
	
	@Autowired
	private MedicalRecordDao medicalrecordDao;
	
	
	public List<MedicalrecordModel> getMedicalrecords() {	
		logger.info("Traitement de la demande de recherche des MedicalRecords");
		return medicalrecordDao.findAll();	
	}

	
	
	public List<MedicalrecordModel> findById(String firstName, String lastName) {
		
		logger.info("Vérification de la validité du nom recherché: {} {}", firstName, lastName);
			
		if (!stringUtilsService.checkStringName(firstName) || !stringUtilsService.checkStringName(lastName) ) {
			throw new DataNotConformException("Nom de recherche non conforme !!");
		} else {
			logger.info("-> {} {} est conforme", firstName, lastName);
			logger.info("-->Lancement de la recherche !");
			return medicalrecordDao.findById(firstName, lastName);	
		}
			     		
	}

	 
	public MedicalrecordModel save(MedicalrecordModel medicalrecord) {
		 
		Boolean firstNameOk = false, lastNameOk = false;
		
		if(medicalrecord.getFirstName() != null ) {firstNameOk= stringUtilsService.checkStringAddress(medicalrecord.getFirstName()); 
		}
		if(medicalrecord.getLastName() != null ) {lastNameOk= stringUtilsService.checkStringAddress(medicalrecord.getLastName()); 
		}
		
		if (firstNameOk & lastNameOk) {			
			logger.info("Lancement création du medicalRecord");
			medicalrecordDao.save(medicalrecord);
			return medicalrecord;
		}else throw new DataNotConformException("** Données entrantes non conformes sur les noms");
     }
	

	public MedicalrecordModel delete(MedicalrecordModel medicalrecord) {
		
		if (medicalrecord != null) {
			logger.info("Traitement de la demande de suppression de {}",medicalrecord);
			return medicalrecordDao.delete(medicalrecord);
		}else {
			logger.info("** Un problème du au format est survenu, suppression impossible pour {} !",medicalrecord);
			return null;
		}
	}

	
	
	public MedicalrecordModel put(MedicalrecordModel medicalrecord) {

		if (medicalrecord != null) {
			logger.info("Traitement de la demande de mise à jour de {}",medicalrecord);
			return medicalrecordDao.put(medicalrecord);
		}else {
			logger.info("** un problème du au format est survenu, maj impossible pour {} !",medicalrecord);
			return null;
		}	
	}


}
