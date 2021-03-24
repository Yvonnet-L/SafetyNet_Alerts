package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordDaoTest {

	@Autowired
	MedicalRecordDao medicalrecordDao;

	MedicalrecordModel medicalrecord;
	MedicalrecordModel medicalrecordPut;

	@BeforeEach
	private void init() throws ParseException {

		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);

		MedicalrecordModel medicalrecord1 = new MedicalrecordModel("firstName1", "lastName", birthday, medications,
				allergies);
		MedicalrecordModel medicalrecord2 = new MedicalrecordModel("firstName2", "lastName", birthday, medications,
				allergies);
		MedicalrecordModel medicalrecord3 = new MedicalrecordModel("firstName3", "lastName", birthday, medications,
				allergies);

		List<MedicalrecordModel> medicalrecordList = new ArrayList<>();
		medicalrecordList.add(medicalrecord1);
		medicalrecordList.add(medicalrecord2);
		medicalrecordList.add(medicalrecord3);
		medicalrecordDao.setAllMedicalrecords(medicalrecordList);

		medicalrecord = new MedicalrecordModel("firstName4", "lastName", birthday, medications, allergies);
		medicalrecordPut = new MedicalrecordModel("firstName3", "lastName", birthday, medications, allergies);
	}

	@Test
	public void findAllMedicalRecordsTest() throws Exception {
		assertThat(medicalrecordDao.findAll().size()).isEqualTo(3);
	}

	@Test
	public void findFirestationByIdTest() throws Exception {
		assertThat(medicalrecordDao.findById("firstName2", "lastName").size()).isEqualTo(1);
		assertThrows(DataNotFoundException.class, () -> medicalrecordDao.findById(" ", ""));
	}

	@Test
	public void postFirestationTest() throws Exception {
		assertThat(medicalrecordDao.save(medicalrecord));
	}

	@Test
	public void putMedicalrecordTest() throws Exception {
		assertThat(medicalrecordDao.put(medicalrecordPut));
	}

	@Test
	public void deleteMedicalrecordTest() throws Exception {
		List<String> medications = null;
		List<String> allergies = null;
		Date birthday = null;
		MedicalrecordModel medicalrecordDelete = new MedicalrecordModel("firstName2", "lastName", birthday, medications,
				allergies);
		assertThat(medicalrecordDao.delete(medicalrecordDelete));
	}

}
