package com.safetynet.safetyAlerts.dao;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetyAlerts.model.DataModel;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;


@ExtendWith(MockitoExtension.class)
public class DataDaoTest {

	@InjectMocks
    private DataDaoImpl dataDao;
	
	@Mock
	PersonDao personDao;

	@Mock
	FirestationDao fireStationDao;

	@Mock
	MedicalRecordDao medicalRecordsDao;
	
	@Mock
	UrlsDao urlsDao;

	@Mock
	DataModel pageData;
	
	@Mock
	ObjectMapper mapper;

	DataModel dataModel = new DataModel();

	File json = new File("src\\main\\resources\\data.json");
	
	List<PersonModel> listPerson = new ArrayList<PersonModel>();
	List<FirestationModel> listFireStation = new ArrayList<FirestationModel>();
	List<MedicalrecordModel> listMedicalRecord = new ArrayList<MedicalrecordModel>();
	
	List<String> medications = null;
	List<String> allergies = null;
	Date birthday = null;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
	@BeforeEach
	public void setUp() throws ParseException, JsonParseException, JsonMappingException, IOException {
		
		listPerson.add(new PersonModel("firstName1","LastName1","address1","city",95000," 06 06 06 06", "mail@mail.com",0));
		listPerson.add(new PersonModel("firstName1","LastName2","address1","city",95000," 06 06 06 06", "mail@mail.com",0));
		listPerson.add(new PersonModel("firstName2","LastName2","address2","city",95000," 06 06 06 06", "mail@mail.com",0));
	
		listFireStation.add(new FirestationModel("fire1","address1"));
		listFireStation.add( new FirestationModel("fire2","address2"));
		listFireStation.add(new FirestationModel("fire2","address3"));
		listFireStation.add( new FirestationModel("fire3","address4"));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);

		listMedicalRecord.add(new MedicalrecordModel("firstName1", "lastName", birthday, medications, allergies));
		listMedicalRecord.add( new MedicalrecordModel("firstName2", "lastName", birthday, medications, allergies));
		listMedicalRecord.add( new MedicalrecordModel("firstName3", "lastName2", birthday, medications, allergies));	
		
		
		dataModel.setFirestations(listFireStation);
		dataModel.setMedicalrecords(listMedicalRecord);
		dataModel.setPersons(listPerson);
		
		pageData = dataModel;
		
		listPerson = pageData.getPersons();
		listFireStation = pageData.getFirestations();
		listMedicalRecord = pageData.getMedicalrecords();
		
	}
	
	@Test
	public void initMapperTest() throws IOException, ParseException {
		
		Mockito.when(mapper.readValue(json, DataModel.class)).thenReturn(dataModel);
		
		dataDao.initMapper();
		
		assertEquals( pageData.getMedicalrecords().size(), 3);

		assertEquals( pageData.getFirestations().size(), 4);	

		assertEquals( pageData.getMedicalrecords().get(0).getAge(), 20);
			
		assertEquals( pageData.getFirestations().get(0).getNbPerson(), 2);
		
		String stringBirthday = "01/01/1982";
		birthday = simpleDateFormat.parse(stringBirthday);
		MedicalrecordModel listMedicalRecord1 = new MedicalrecordModel("firstName3", "lastName2", birthday, medications, allergies);
		listMedicalRecord.add(listMedicalRecord1);
		
		dataDao.upDateData();
		
		assertEquals( pageData.getMedicalrecords().size(), 4);
	
		assertEquals( pageData.getMedicalrecords().get(3).getAge(), 39);

				
	}

}
