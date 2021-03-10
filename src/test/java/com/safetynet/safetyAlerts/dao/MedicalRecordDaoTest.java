package com.safetynet.safetyAlerts.dao;


	
	import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.model.MedicalrecordModel;

	@SpringBootTest
	@AutoConfigureMockMvc
	public class MedicalRecordDaoTest {

		@Autowired
		MedicalRecordDao medicalrecordDao;

		@Test
		public void findAllMedicalRecordsTest() throws Exception {
			Thread.sleep(1500);
			assertThat(medicalrecordDao.findAll().size()).isEqualTo(24);
		}
		
		@Test
		public void findFirestationByIdTest() throws Exception {
			assertThat(medicalrecordDao.findById("Jacob","Boyd").size()).isEqualTo(1);
			assertThat(medicalrecordDao.findById(" ","").size()).isEqualTo(0);
			assertThat(medicalrecordDao.findById("","").size()).isEqualTo(0);
		}
		
		@Test
		public void postFirestationTest() throws Exception {
			
			List<String> medications = null;
			List<String> allergies = null;
			Date birthday = null; 
			MedicalrecordModel medicalrecordPost= new MedicalrecordModel("firstName", "lastName", birthday, medications, allergies);
			assertThat(medicalrecordDao.put(medicalrecordPost));	
		}
		

		@Test
		public void postMedicalrecordWithSpaceTest() throws Exception {
			List<String> medications = null;
			List<String> allergies = null;
			Date birthday = null; 
			MedicalrecordModel medicalrecordPost= new MedicalrecordModel(" ", " ", birthday, medications, allergies);
			assertThat(medicalrecordDao.save(medicalrecordPost));
		}
		
		@Test
		public void putMedicalrecordTest() throws Exception {
			
			List<String> medications = null;
			List<String> allergies = null;
			Date birthday = null; 
			MedicalrecordModel medicalrecordPut= new MedicalrecordModel("firstName", "lastName", birthday, medications, allergies);
			assertThat(medicalrecordDao.put(medicalrecordPut));	
		}
		
		@Test
		public void deleteMedicalrecordTest() throws Exception {
			List<String> medications = null;
			List<String> allergies = null;
			Date birthday = null; 
			MedicalrecordModel medicalrecordDelete= new MedicalrecordModel("firstName2", "lastName", birthday, medications, allergies);
			assertThat(medicalrecordDao.delete(medicalrecordDelete));
		}

		
		@Test
		public void deleteMedicalrecordWithJsonNullErrorTest() throws Exception {
			List<String> medications = null;
			List<String> allergies = null;
			Date birthday = null; 
			MedicalrecordModel medicalrecordDelete= new MedicalrecordModel(" ", " ", birthday, medications, allergies);
			assertThat(medicalrecordDao.delete(medicalrecordDelete));
		}

}
