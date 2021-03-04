package com.safetynet.safetyAlerts.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetyAlerts.model.DataModel;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.AgeCalculService;
import com.safetynet.safetyAlerts.service.MapperService;

@Repository
public class DataDaoImpl implements DataDao {

	
	 private static Logger logger = LoggerFactory.getLogger(MapperService.class);
	
	   // @Autowired
	    //PersonDao personDao;
	    
	    @Autowired
	    FirestationDao fireStationDao;
	    
	    //@Autowired
	    //MedicalrecordDao medicalRecordsDao;
	   
		 
	    List<PersonModel> listPerson = new ArrayList<PersonModel>();
	    List<FirestationModel> listFireStation = new ArrayList<FirestationModel>();
	    List<MedicalrecordModel> listMedicalRecord = new ArrayList<MedicalrecordModel>();
		
		
	    @PostConstruct
	    @Override
		public void InitMapper() throws IOException {
	    	
	    	
			 ObjectMapper mapper = new ObjectMapper();
			 int age = 0;
		        try {
		        	logger.info("Lancement du rapatriement des donnees du Json en cour");
		        	DataModel pageData = mapper.readValue(new File("src\\main\\resources\\data.json"), DataModel.class);

		            listPerson = pageData.getPersons();
		            listFireStation = pageData.getFirestations();
		            listMedicalRecord = pageData.getMedicalrecords();
		            
		            
		          //  personDao.setAllPersons(listPerson);
		            fireStationDao.setAllFireStations(listFireStation);
		          //  medicalRecordsDao.setAllMedicalrecords(listMedicalRecord); 

		            for (MedicalrecordModel m: listMedicalRecord) {
		            	AgeCalculService ageCalcul= new AgeCalculService();
		            	age = 0;
		            	age = ageCalcul.personCalulateAge(m.getBirthdate(), age);
		            	m.setAge(age);
		            	
			            	for (PersonModel p: listPerson) {
			            		
			            		if (p.getFirstName().equals(m.getFirstName()) & p.getLastName().equals(m.getLastName())) {
			            			p.setMedicalrecord(m);
			            			p.setAge(age);
			            		}
			            		for(FirestationModel f: listFireStation) {
				            		if (f.getAddress().equals(p.getAddress())) {
				            			p.setFirestation(f.getStation());
				            		}
			            		}
			            	}
		            }  	
			            	for(FirestationModel f: listFireStation) {
			            		 List<PersonModel> listPersonsFire = new ArrayList<PersonModel>();
			            		 int nbPerson = 0;
			            		 for (PersonModel p: listPerson) {
				            		 if (f.getAddress().equals(p.getAddress())) {
					            			listPersonsFire.add(p);
					            			nbPerson ++;
					            		}
				            	}
			            		f.setPerson(listPersonsFire); 
			            		f.setNbPerson(nbPerson);
				            }
			            	logger.info("Traitement du Mapper terminé, données rapatriées!");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }  	
		        
		    }
}