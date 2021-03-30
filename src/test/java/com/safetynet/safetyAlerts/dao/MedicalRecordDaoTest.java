package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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

import com.safetynet.safetyAlerts.exceptions.DataExistException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordDaoTest {

	@InjectMocks
	MedicalRecordDaoImpl medicalrecordDao;

	@Mock
	MedicalRecordDao medicalRecordDao;
	
	
	public List<MedicalrecordModel> medicalrecords = new ArrayList<>();
	public List<PersonModel> persons = new ArrayList<>();

	
	MedicalrecordModel medicalrecord;
	MedicalrecordModel medicalrecordPut;
	List<String> medications = null;
	List<String> allergies = null;
	Date birthday = null;

	@BeforeEach
	private void setUp() throws ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);

		medicalrecords.add(new MedicalrecordModel("firstName1", "lastName", birthday, medications, allergies));
		medicalrecords.add( new MedicalrecordModel("firstName2", "lastName", birthday, medications, allergies));
		medicalrecords.add( new MedicalrecordModel("firstName3", "lastName2", birthday, medications, allergies));			
		medicalrecordDao.setAllMedicalrecords(medicalrecords);
		
		persons.add(new PersonModel("firstName1", "lastName", "address1", "CityTest", 10000, "telTest", "test@test.com", 15));
		persons.add(new PersonModel("firstName2", "lastName", "address1", "CityTest", 10000, "telTest", "test@test.com", 15));
		persons.add(new PersonModel("firstName3", "lastName2", "addressTest", "CityTest", 10000, "telTest", "test@test.com", 15));
		medicalrecordDao.setAllPersons(persons);
		
	}

	@Test
	public void findAllMedicalRecordsTest() {
		assertThat(medicalrecordDao.findAll().size()).isEqualTo(3);
	}

	@Test
	public void findFirestationByIdTest() {
		
		assertThat(medicalrecordDao.findById("firstName2", "lastName").size()).isEqualTo(1);
		assertThrows(DataNotFoundException.class, () -> medicalrecordDao.findById(" ", ""));
	}

	@Test
	public void postFirestationTest() {	
		
		int size = medicalrecordDao.findAll().size()+1;
		medicalrecord = new MedicalrecordModel("firstName4", "lastName", birthday, medications, allergies);	

		assertThat(medicalrecordDao.save(medicalrecord)).isNotNull();
		assertEquals(medicalrecordDao.findAll().size(),size);		
		assertThrows(DataExistException.class, () -> medicalrecordDao.save(medicalrecord));
	}

	@Test
	public void putMedicalrecordTest() {
		
		medications = new ArrayList<String>();
		medications.add("aspirine 500");
		
		medicalrecordPut = new MedicalrecordModel("firstName2", "lastName", birthday, medications, allergies);
		MedicalrecordModel medicalrecordResult = new MedicalrecordModel();
		medicalrecordResult = medicalrecordDao.put(medicalrecordPut);
		
		assertThat(medicalrecordDao.put(medicalrecordPut)).isNotNull();
		assertEquals(medicalrecordResult.getMedications(), medicalrecordPut.getMedications());
		
		MedicalrecordModel medicalrecordNotExist = new MedicalrecordModel("firstNameNotExist", "lastNameNotExist", birthday, medications, allergies);
		assertThrows(DataNotFoundException.class, () -> medicalrecordDao.put(medicalrecordNotExist));
	}

	@Test
	public void deleteMedicalrecordTest() {
		
		int size = medicalrecordDao.findAll().size()-1;
		MedicalrecordModel medicalrecordDelete = new MedicalrecordModel("firstName2", "lastName", birthday, medications,allergies);
		
		assertThat(medicalrecordDao.delete(medicalrecordDelete)).isNotNull();
		assertEquals(medicalrecordDao.findAll().size(),size);
		assertThrows(DataNotFoundException.class, () -> medicalrecordDao.delete(medicalrecordDelete));
	}

}
