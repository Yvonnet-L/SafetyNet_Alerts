package com.safetynet.safetyAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetyAlerts.dao.MedicalRecordDao;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordServiceTest {

	@Autowired
	public MockMvc mockMvc;

	@Autowired
	MedicalRecordDao medicalrecordDao;

	@Autowired
	MedicalRecordService medicalrecordService;

	@Test
	public void getMedicalrecordTest() throws Exception {
		Thread.sleep(1500);
		assertThat(medicalrecordService.getMedicalrecords().size()).isEqualTo(24);
	}

	@Test
	public void findMedicalrecordByIdTest() throws Exception {
		assertThat(medicalrecordService.findById("Eric", "Cadigan").size()).isEqualTo(1);
	}

	@Test
	public void postMedicalrecordTest() throws Exception {
		int size = 0;
		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);
		MedicalrecordModel medicalrecordPost = new MedicalrecordModel("firstNameTest", "lastNameTest", birthday,
				medications, allergies);
		size = medicalrecordService.getMedicalrecords().size() + 1;
		assertThat(medicalrecordService.addMedicalRecord(medicalrecordPost)).isNotNull();
		assertThat(medicalrecordService.getMedicalrecords().size()).isEqualTo(size);
	}

	@Test
	public void putFirestationByAddressTest() throws Exception {
		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);
		MedicalrecordModel medicalrecordPut = new MedicalrecordModel("firstNameTest", "lastNameTest", birthday,
				medications, allergies);
		assertThat(medicalrecordService.upDateMedicalrecord(medicalrecordPut)).isNotNull();
	}

	@Test
	public void updateFirestationWithJsonNullErrorTest() throws Exception {
		MedicalrecordModel medicalrecordDelete = null;
		assertThat(medicalrecordService.deleteMedicalRecord(medicalrecordDelete));
	}

	@Test
	public void deleteMedicalrecordTest() throws Exception {
		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;
		MedicalrecordModel medicalrecordDelete = new MedicalrecordModel("firstName2", "lastName", birthday, medications,
				allergies);
		assertThat(medicalrecordService.deleteMedicalRecord(medicalrecordDelete)).isNull();
	}

	@Test
	public void deleteMedicalrecordWithJsonNullErrorTest() throws Exception {
		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;
		MedicalrecordModel medicalrecordDelete = new MedicalrecordModel(" ", " ", birthday, medications, allergies);
		assertThat(medicalrecordService.deleteMedicalRecord(medicalrecordDelete));
	}

}
