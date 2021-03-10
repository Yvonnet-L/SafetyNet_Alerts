package com.safetynet.safetyAlerts.dao;

import java.util.List;

import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;



public interface MedicalRecordDao {


	public List<MedicalrecordModel> findAll();
	
	public List<MedicalrecordModel> findById(String firstname,String lastname);
	
	public MedicalrecordModel save(MedicalrecordModel medicalrecord);
	
	public MedicalrecordModel delete(MedicalrecordModel medicalrecord);

	public void setAllMedicalrecords(List<MedicalrecordModel> listMedicalrecord);

	public MedicalrecordModel put(MedicalrecordModel medicalrecord);

	void setAllPersons(List<PersonModel> listPerson);

	void updateData();
}
