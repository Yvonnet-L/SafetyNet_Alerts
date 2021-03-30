package com.safetynet.safetyAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetyAlerts.dao.MedicalRecordDao;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {


	@InjectMocks
	MedicalRecordService medicalrecordService;
	
	@Mock
	MedicalRecordDao medicalrecordDao;
	
	public List<MedicalrecordModel> medicalrecords = new ArrayList<>();
	
	MedicalrecordModel medicalrecord;
	
	List<String> medications = null;
	List<String> allergies = null;
	Date birthday = null;
	MedicalrecordModel medicalrecordConform1 = new MedicalrecordModel("##", "lastNameTest", birthday, medications, allergies);
	MedicalrecordModel medicalrecordConform2 = new MedicalrecordModel("firstNameTest", "**", birthday, medications, allergies);
	
	@BeforeEach
	private void setUp() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);
		medicalrecord = new MedicalrecordModel("firstNameTest", "lastNameTest", birthday, medications, allergies);
	}
		
	@Test
	public void getMedicalrecordTest() throws Exception {
		// GIVEN	
		medicalrecords.add(new MedicalrecordModel("firstName1", "lastName", birthday, medications, allergies));
		medicalrecords.add(new MedicalrecordModel("firstName2", "lastName", birthday, medications, allergies));
		medicalrecords.add(new MedicalrecordModel("firstName3", "lastName2", birthday, medications, allergies));
		// WHEN
		Mockito.when(medicalrecordDao.findAll()).thenReturn(medicalrecords);
		// THEN
		assertThat(medicalrecordService.getMedicalrecords().size()).isEqualTo(3);
		
	}

	@Test
	public void findMedicalrecordByIdTest() throws Exception {
		// GIVEN
		medicalrecords.add(new MedicalrecordModel("firstName1", "lastName", birthday, medications, allergies));
		// WHEN
		Mockito.when(medicalrecordDao.findById("firstName1", "lastName")).thenReturn(medicalrecords);
		// THEN
		assertThat(medicalrecordService.findById("firstName1", "lastName").size()).isEqualTo(1);
	}
	
	@Test
	public void findMedicalrecordByIdNotConformTest() throws Exception {	
		assertThrows(DataNotConformException.class, () -> medicalrecordService.findById("##", "lasNameTest"));
		assertThrows(DataNotConformException.class, () -> medicalrecordService.findById("fisrtNameNoExist", "***"));		
	}

	@Test
	public void postMedicalrecordTest() throws Exception {	
		// WHEN
		Mockito.when(medicalrecordDao.save(medicalrecord)).thenReturn(medicalrecord);
		// THEN
		assertThat(medicalrecordService.addMedicalRecord(medicalrecord)).isEqualTo(medicalrecord);
	}

	@Test
	public void putFirestationByAddressTest() throws Exception {
		// WHEN
		Mockito.when(medicalrecordDao.put(medicalrecord)).thenReturn(medicalrecord);
		// THEN
		assertThat(medicalrecordService.upDateMedicalrecord(medicalrecord)).isEqualTo(medicalrecord);
	}

	@Test
	public void deleteMedicalrecordTest() throws Exception {
		// WHEN
		Mockito.when(medicalrecordDao.delete(medicalrecord)).thenReturn(medicalrecord);
		// THEN
		assertThat(medicalrecordService.deleteMedicalRecord(medicalrecord)).isEqualTo(medicalrecord);
	}

	@Test
	public void addMedicalrecordWithStationNotConformTest() throws Exception {	
		assertThrows(DataNotConformException.class, () -> medicalrecordService.addMedicalRecord(medicalrecordConform1));
		assertThrows(DataNotConformException.class, () -> medicalrecordService.addMedicalRecord(medicalrecordConform2));
	}
	
	@Test
	public void deleteMedicalrecordWithStationNotConformTest() throws Exception {	
		assertThrows(DataNotConformException.class, () -> medicalrecordService.deleteMedicalRecord(medicalrecordConform1));
		assertThrows(DataNotConformException.class, () -> medicalrecordService.deleteMedicalRecord(medicalrecordConform2));
	}
	
	@Test
	public void updateFirestationWithStationNotConformTestTest() throws Exception {	
		assertThrows(DataNotConformException.class, () -> medicalrecordService.upDateMedicalrecord(medicalrecordConform1));
		assertThrows(DataNotConformException.class, () -> medicalrecordService.upDateMedicalrecord(medicalrecordConform2));	
	}


}
