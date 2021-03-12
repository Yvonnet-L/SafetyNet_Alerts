package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;
import com.safetynet.safetyAlerts.service.AgeCalculService;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

	private static Logger logger = LoggerFactory.getLogger(MedicalRecordDaoImpl.class);

	public List<MedicalrecordModel> medicalrecords = new ArrayList<>();
	public List<PersonModel> persons = new ArrayList<>();

	@Override
	public List<MedicalrecordModel> findAll() {
		logger.info("--> Liste des MedicalRecords: {}", medicalrecords);
		return medicalrecords;
	}

	@Override
	public List<MedicalrecordModel> findById(String firstname, String lastname) {

		List<MedicalrecordModel> res = new ArrayList<>();

		for (MedicalrecordModel medicalrecord : medicalrecords) {
			if (medicalrecord.getFirstName().equals(firstname) & medicalrecord.getLastName().equals(lastname)) {
				res.add(medicalrecord);
			}
		}
		logger.info("--> Liste des MedicalRecords par id {} {}: {}", firstname, lastname, res);
		return res;
	}

	@Override
	public MedicalrecordModel save(MedicalrecordModel medicalrecord) {
		MedicalrecordModel medicalrecordSelect = null;
		if ((medicalrecord.getFirstName().isBlank() || medicalrecord.getLastName().isBlank())) {
			return medicalrecord;
		} else {
			for (MedicalrecordModel m : medicalrecords) {
				if (m.getFirstName().equals(medicalrecord.getFirstName())
						& m.getLastName().equals(medicalrecord.getLastName())) {
					medicalrecordSelect = m;
				}
			}
			if (medicalrecordSelect == null) {
				medicalrecords.add(medicalrecord);
				logger.info("--> MedicalRecord ajouté: {}", medicalrecord);
				updateData();
				return medicalrecord;
			} else {
				logger.info("*** Un MedicalRecord pour {} {} est déjà existant:  {} !", medicalrecord.getFirstName(),
						medicalrecord.getLastName(), medicalrecordSelect);
				return null;
			}
		}
	}

	@Override
	public MedicalrecordModel delete(MedicalrecordModel medicalrecord) {
		MedicalrecordModel medicalrecordSelect = null;

		if ((medicalrecord.getFirstName().isBlank() || medicalrecord.getLastName().isBlank())) {
			return medicalrecord;
		} else {
			for (MedicalrecordModel m : medicalrecords) {
				if (m.getFirstName().equals(medicalrecord.getFirstName())
						& m.getLastName().equals(medicalrecord.getLastName())) {
					medicalrecordSelect = m;
				}
			}

			if (medicalrecordSelect == null) {
				logger.info("--> Aucun MedicalRecord trouvé pour {} {} !", medicalrecord.getFirstName(),
						medicalrecord.getLastName());
				return null;
			} else {
				medicalrecords.remove(medicalrecordSelect);
				logger.info("--> MedicalRecord supprimé pour {} {} !", medicalrecord.getFirstName(),
						medicalrecord.getLastName());
				updateData();
				return medicalrecord;
			}
		}
	}

	@Override
	public MedicalrecordModel put(MedicalrecordModel medicalrecord) {

		MedicalrecordModel medicalrecordSelect = new MedicalrecordModel();
		medicalrecordSelect = null;
		if ((medicalrecord.getFirstName().isBlank() || medicalrecord.getLastName().isBlank())) {
			return medicalrecord;
		} else {
			for (MedicalrecordModel m : medicalrecords) {
				if (m.getFirstName().equals(medicalrecord.getFirstName())
						& m.getLastName().equals(medicalrecord.getLastName())) {
					medicalrecords.set(medicalrecords.indexOf(m), medicalrecord);
					medicalrecordSelect = m;
				}
			}
			if (medicalrecordSelect == null) {
				logger.info("--> MédicalRecord Non trouvé pour {} {} !", medicalrecord.getFirstName(),
						medicalrecord.getLastName());
			} else {
				logger.info("--> MédicalRecord de {} {} Modifié !", medicalrecord.getFirstName(),
						medicalrecord.getLastName());
				updateData();
			}
			return medicalrecord;
		}
	}

	// ************************* Set All ************************************
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

	// ******************** updateData  ***************************

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
