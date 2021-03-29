package com.safetynet.safetyAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.dao.MedicalRecordDao;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;

@SpringBootTest
public class MedicalRecordServiceTest {


	@Autowired
	MedicalRecordDao medicalrecordDao;

	@Autowired
	MedicalRecordService medicalrecordService;
	
	MedicalrecordModel medicalrecord;
	
	@BeforeEach
	private void init() throws ParseException {

		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);
		medicalrecord = new MedicalrecordModel("firstNameTest", "lastNameTest", birthday, medications, allergies);

	}
	

	
	@Test
	public void getMedicalrecordTest() throws Exception {
		assertThat(medicalrecordService.getMedicalrecords().size()).isEqualTo(23);
	}

	@Test
	public void findMedicalrecordByIdTest() throws Exception {
		assertThat(medicalrecordService.findById("John", "Boyd").size()).isEqualTo(1);
	}
	
	@Test
	public void findMedicalrecordByIdNoExistTest() throws Exception {
		assertThrows(DataNotFoundException.class, () -> medicalrecordService.findById("FisrtNameNoExist", "LasNameNoExist"));
		
	}

	@Test
	public void postMedicalrecordTest() throws Exception {
		int size = 0;
		size = medicalrecordService.getMedicalrecords().size() + 1;
		
		assertThat(medicalrecordService.addMedicalRecord(medicalrecord)).isNotNull();
		assertThat(medicalrecordService.getMedicalrecords().size()).isEqualTo(size);
		medicalrecordDao.delete(medicalrecord);
		
	}

	@Test
	public void putFirestationByAddressTest() throws Exception {
		medicalrecordDao.save(medicalrecord);
		assertThat(medicalrecordService.upDateMedicalrecord(medicalrecord)).isNotNull();
		medicalrecordDao.delete(medicalrecord);
	}

	@Test
	public void deleteMedicalrecordTest() throws Exception {
		int size = 0;
		medicalrecordDao.save(medicalrecord);
		size = medicalrecordService.getMedicalrecords().size()-1;
		assertThat(medicalrecordService.deleteMedicalRecord(medicalrecord)).isSameAs(medicalrecord);
		assertThat(medicalrecordService.getMedicalrecords().size()).isEqualTo(size);
	}

	@Test
	public void deleteMedicalrecordWithJsonNullErrorTest() throws Exception {
		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;
		
		MedicalrecordModel medicalrecordDelete = new MedicalrecordModel(" ", " ", birthday, medications, allergies);
		assertThrows(DataNotConformException.class, () -> medicalrecordService.deleteMedicalRecord(medicalrecordDelete));
	}

}
