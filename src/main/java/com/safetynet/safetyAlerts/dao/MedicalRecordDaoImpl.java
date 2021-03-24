package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.exceptions.DataExistException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.AgeCalculService;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

	private static Logger logger = LoggerFactory.getLogger(MedicalRecordDaoImpl.class);

	public List<MedicalrecordModel> medicalrecords = new ArrayList<>();
	public List<PersonModel> persons = new ArrayList<>();

	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<MedicalrecordModel> findAll() {
		logger.info("--> Liste des MedicalRecords: {}", medicalrecords);
		return medicalrecords;
	}

	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<MedicalrecordModel> findById(String firstName, String lastName) {

		List<MedicalrecordModel> listResult = new ArrayList<MedicalrecordModel>();

		for (MedicalrecordModel medicalrecord : medicalrecords) {
			if (firstName.equals(medicalrecord.getFirstName()) & lastName.equals(medicalrecord.getLastName())) {
				listResult.add(medicalrecord);
			}
		}
		if (listResult.isEmpty()) {		
			throw new DataNotFoundException("** Aucune personne trouvée pour " + firstName + " " + lastName);
		} else {
			logger.info("--> Liste des MedicalRecords pour {} {}: {}", firstName, lastName, listResult);
			return listResult;
		}
	}	
	//--------------------------------------------------------------------------------------------------------
	@Override
	public MedicalrecordModel save(MedicalrecordModel medicalrecord) {
		
		List<MedicalrecordModel> listMedic = new ArrayList<MedicalrecordModel>();
		
			for (MedicalrecordModel m : medicalrecords) {
				if  ((medicalrecord.getFirstName().equals(m.getFirstName())) & (medicalrecord.getLastName().equals(m.getLastName()))) {
					listMedic.add(m);
				}
			}
			
			if (listMedic.isEmpty()) {
				medicalrecords.add(medicalrecord);
				logger.info("--> MedicalRecord ajouté: {}", medicalrecord);
				updateData();
				return medicalrecord;
			} else throw new DataExistException("*** Un MedicalRecord à ce nom " + medicalrecord.getFirstName() + " " + 
																		medicalrecord.getLastName() + " existe déjà !");	
	}

	//--------------------------------------------------------------------------------------------------------
	@Override
	public MedicalrecordModel delete(MedicalrecordModel medicalrecord) {
		
		List<MedicalrecordModel> listMedic = new ArrayList<MedicalrecordModel>();
		
			for (MedicalrecordModel m : medicalrecords) {
				if ((medicalrecord.getFirstName().equals(m.getFirstName())) & (medicalrecord.getLastName().equals(m.getLastName()))) {
				listMedic.add(m);
			}
		}

		if (listMedic.isEmpty()) {
			throw new DataNotFoundException("** Aucune personne trouvée pour " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName());
		} else {
			logger.info("--> MedicalRecord supprimé pour {} ", listMedic);
			for (MedicalrecordModel m: listMedic) {
				medicalrecords.remove(m);
			}		
			updateData();
			return medicalrecord;
		}
		
	}

	//--------------------------------------------------------------------------------------------------------
	@Override
	public MedicalrecordModel put(MedicalrecordModel medicalrecord) {

		MedicalrecordModel medicalrecordSelect = new MedicalrecordModel();
		medicalrecordSelect = null;
		
			for (MedicalrecordModel m : medicalrecords) {
				if ((medicalrecord.getFirstName().equals(m.getFirstName())) & (medicalrecord.getLastName().equals(m.getLastName()))) {
					medicalrecords.set(medicalrecords.indexOf(m), medicalrecord);
					medicalrecordSelect = m;
				}
			}
			if (medicalrecordSelect == null) {
				throw new DataNotFoundException("Aucune personne trouvée pour " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName());
			} else {
				logger.info("--> MédicalRecord de {} {} Modifié !", medicalrecord.getFirstName(), medicalrecord.getLastName());
				updateData();
			}
			return medicalrecord;
		
	}

	//--------------------------** Set All **-----------------------------------------------------------------
	
	@Override
	public void setAllMedicalrecords(List<MedicalrecordModel> listMedicalrecord) {
		logger.info("--> SetAllMedic {}", listMedicalrecord);
		this.medicalrecords = listMedicalrecord;
	}

	@Override
	public void setAllPersons(List<PersonModel> listPerson) {
		logger.info("--> SetAllPerson de MedicalRecord {}", listPerson);
		this.persons = listPerson;
	}

	//-------------------------** updateData  **-------------------------------------------------------------

	@Override
	public void updateData() {
		logger.info("---> Lancement de UpdateData !");
		AgeCalculService ageCalcul = new AgeCalculService();
		int age = 0;
		for (PersonModel p : persons) {
			p.setMedicalrecord(null);
			p.setAge(0);
		}
		for (MedicalrecordModel m : medicalrecords) {
			age = 0;
			age = ageCalcul.personCalulateAge(m.getBirthdate(), age);
			m.setAge(age);
			for (PersonModel p : persons) {

				if (p.getFirstName().equals(m.getFirstName()) & p.getLastName().equals(m.getLastName())) {
					p.setMedicalrecord(m);
					p.setAge(m.getAge());
				}
			}
		}
		logger.info("----> Update terminé, données mises à jour !");
	}
}
