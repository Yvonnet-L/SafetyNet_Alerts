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
	
	
	//--------------------------------------------------------------------------------------------------------	
	public List<MedicalrecordModel> getMedicalrecords() {	
		logger.info("Traitement de la demande de recherche des MedicalRecords");
		return medicalrecordDao.findAll();	
	}

	//--------------------------------------------------------------------------------------------------------	
	public List<MedicalrecordModel> findById(String firstName, String lastName) {
		
		MedicalrecordModel medicalrecord = new MedicalrecordModel();
		medicalrecord.setFirstName(firstName);
		medicalrecord.setLastName(lastName);
		Boolean namesOk = medicalRecordTestNames(medicalrecord);
		//stringUtilsService.checkStringAddress(firstName) & stringUtilsService.checkStringAddress(firstName)
		if ( namesOk ) {
			logger.info("-> {} {} sont conforme", firstName, lastName);
			logger.info("-->Lancement de la recherche !");
			return medicalrecordDao.findById(firstName, lastName);		
		} else throw new DataNotConformException("** Nom de recherche non conforme !!");			     		
	}

	//--------------------------------------------------------------------------------------------------------	 
	public MedicalrecordModel addMedicalRecord(MedicalrecordModel medicalrecord) {
		 
		Boolean namesOk = medicalRecordTestNames(medicalrecord);
		
		if (namesOk) {			
			logger.info("Lancement création du medicalRecord");
			medicalrecordDao.save(medicalrecord);
			return medicalrecord;
		}else throw new DataNotConformException("** Données entrantes non conformes sur les noms");
     }
	
	//--------------------------------------------------------------------------------------------------------
	public MedicalrecordModel deleteMedicalRecord(MedicalrecordModel medicalrecord) {
		
		Boolean namesOk = medicalRecordTestNames(medicalrecord);
		
		if (namesOk) {
			logger.info("Traitement de la demande de suppression de {}",medicalrecord);
			return medicalrecordDao.delete(medicalrecord);
		}else throw new DataNotConformException("** Données entrantes non conformes sur les noms,  suppression impossible !");
		
	}
	
	//--------------------------------------------------------------------------------------------------------	
	public MedicalrecordModel upDateMedicalrecord(MedicalrecordModel medicalrecord) {
		
		Boolean namesOk = medicalRecordTestNames(medicalrecord);
		
		if (namesOk) {
			logger.info("Traitement de la demande de mise à jour de {}",medicalrecord);
			return medicalrecordDao.put(medicalrecord);
		}else throw new DataNotConformException("** Données entrantes non conformes sur les noms,  update impossible !");		
	}
	
	//--------------------** Utility method **--------------------------------------------------------
	
	public boolean medicalRecordTestNames(MedicalrecordModel medicalrecord) {
		
		Boolean firstNameOk = false, lastNameOk = false;
		
		if(medicalrecord.getFirstName() != null ) { firstNameOk = stringUtilsService.checkStringAddress(medicalrecord.getFirstName()); 
		}
		if(medicalrecord.getLastName() != null ) { lastNameOk = stringUtilsService.checkStringAddress(medicalrecord.getLastName()); 
		}
		
		if (firstNameOk & lastNameOk ) {
			return true;
		} else {
			return false;
		}
	}

}
