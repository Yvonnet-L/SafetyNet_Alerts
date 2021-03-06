package com.safetynet.safetyAlerts.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetyAlerts.dao.MedicalRecordDao;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;

@Service
public class MedicalRecordService {
	
	private static Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);

	private List<MedicalrecordModel> medicalrecords = new ArrayList<>();
	
	@Autowired
	private MedicalRecordDao medicalrecordDao;

	public List<MedicalrecordModel> getFirestations() {
		logger.info("Traitement de la demande de recherche des MedicalRecords");
		return medicalrecordDao.findAll();	
	}

	public List<MedicalrecordModel> findById(String firstname, String lastname) {
		
        if (firstname.isEmpty() || firstname.isBlank() || lastname.isEmpty() || lastname.isBlank()) {
        	logger.info("** Recherche avortée, id non conforme: {} {}",firstname,lastname);
        	return null;
        }else {
        	 logger.info("Lancement de la recherche pour  {} {}",firstname, lastname);
        	return medicalrecordDao.findById(firstname, lastname);
        	
        }
  	     		
	}

	 public MedicalrecordModel save(MedicalrecordModel medicalrecord) {
		 if (medicalrecord != null) {
			 logger.info("Traitement de la demande de création du MedicalRecord: {}", medicalrecord);
			 return medicalrecordDao.save(medicalrecord);
		 }else {
			 logger.info("--> Création avortée, format non conforme");
			 return null;
		 }
     	
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
