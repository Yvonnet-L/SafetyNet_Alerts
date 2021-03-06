package com.safetynet.safetyAlerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.safetyAlerts.model.MedicalrecordModel;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

	private static Logger logger = LoggerFactory.getLogger(MedicalRecordDaoImpl.class);

	private List<MedicalrecordModel> medicalrecords = new ArrayList<>();

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
		for (MedicalrecordModel m : medicalrecords) {
			if (m.getFirstName().equals(medicalrecord.getFirstName())
					& m.getLastName().equals(medicalrecord.getLastName())) {
				medicalrecordSelect = m;
			}
		}
		if (medicalrecordSelect == null) {
			medicalrecords.add(medicalrecord);
			logger.info("--> MedicalRecord ajouté: {}", medicalrecord);
			return medicalrecord;
		} else {
			logger.info("*** Un MedicalRecord pour {} {} est déjà existant:  {} !", medicalrecord.getFirstName(),
					medicalrecord.getLastName(), medicalrecordSelect);
			return null;
		}

	}

	@Override
	public MedicalrecordModel delete(MedicalrecordModel medicalrecord) {
		MedicalrecordModel medicalrecordSelect = null;

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
			logger.info("--> MedicalRecord supprimé pour {} {} !", medicalrecord.getFirstName(),
					medicalrecord.getLastName());

			medicalrecords.remove(medicalrecordSelect);
			return medicalrecord;
		}
	}

	@Override
	public MedicalrecordModel put(MedicalrecordModel medicalrecord) {

		MedicalrecordModel medicalrecordSelect = new MedicalrecordModel();
		medicalrecordSelect = null;
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
		}
		return medicalrecord;
	}

	public void setAllMedicalrecords(List<MedicalrecordModel> listMedicalrecord) {
		this.medicalrecords = listMedicalrecord;

	}

}
