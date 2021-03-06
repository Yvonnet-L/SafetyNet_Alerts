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
import org.junit.jupiter.api.DisplayName;
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
	Date birthdayAdult = null;
	Date birthdayChild = null;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
	@BeforeEach
	public void setUp() throws ParseException, JsonParseException, JsonMappingException, IOException {
		
		listPerson.add(new PersonModel("firstName1","LastName1","address1","city",95000," 06 06 06 06", "mail1@mail.com",0));
		listPerson.add(new PersonModel("firstName1","LastName2","address1","city",95000," 06 06 06 06", "mail1@mail.com",0));
		listPerson.add(new PersonModel("firstName2","LastName2","address2","city",95000," 06 07 07 07", "mail2@mail.com",0));
		listPerson.add(new PersonModel("firstName2","LastName3","address3","city",95000," 06 08 08 08", "mail2@mail.com",0));
		listPerson.add(new PersonModel("firstName3","LastName3","address3","city",95000," 06 09 09 06", "mail3@mail.com",0));
	
		listFireStation.add(new FirestationModel("fire1","address1"));
		listFireStation.add( new FirestationModel("fire2","address2"));
		listFireStation.add(new FirestationModel("fire2","address3"));
		listFireStation.add( new FirestationModel("fire3","address4"));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String stringBirthdayAdult = "01/01/2001";
		String stringBirthdayChild = "01/01/2011";
		birthdayAdult = simpleDateFormat.parse(stringBirthdayAdult);
		birthdayChild = simpleDateFormat.parse(stringBirthdayAdult);
		

		listMedicalRecord.add(new MedicalrecordModel("firstName1", "lastName1", birthdayAdult, medications, allergies));
		listMedicalRecord.add( new MedicalrecordModel("firstName1", "lastName2", birthdayChild, medications, allergies));
		listMedicalRecord.add( new MedicalrecordModel("firstName2", "lastName2", birthdayAdult, medications, allergies));
		listMedicalRecord.add( new MedicalrecordModel("firstName2", "lastName3", birthdayAdult, medications, allergies));
		listMedicalRecord.add( new MedicalrecordModel("firstName3", "lastName3", birthdayChild, medications, allergies));
		
		
		dataModel.setFirestations(listFireStation);
		dataModel.setMedicalrecords(listMedicalRecord);
		dataModel.setPersons(listPerson);
		
		pageData = dataModel;
		
		listPerson = pageData.getPersons();
		listFireStation = pageData.getFirestations();
		listMedicalRecord = pageData.getMedicalrecords();
		
		Mockito.when(mapper.readValue(json, DataModel.class)).thenReturn(dataModel);
		
		dataDao.initMapper();
		
	}
	
	@Test
	@DisplayName("Test du bon fonctionnement de initMapper()")
	public void initMapperTest() throws IOException, ParseException {
		
		assertEquals( pageData.getMedicalrecords().size(), 5);		
		//vérification que l'age est bien calculé
		assertEquals( pageData.getMedicalrecords().get(0).getAge(), 20);
		//vérification de maj des données dans firestation	
		assertEquals( pageData.getFirestations().size(), 4);
		assertEquals( pageData.getFirestations().get(0).getNbPerson(), 2);			
	}
	
	@Test
	@DisplayName("Test du bon fonctionnement de upDateDataTest()")
	public void upDateDataTest() throws IOException, ParseException {
		
		//ajout d'un medicalrecord pour verification du bon fonctionnement upDateData()
		
		Date birthday = simpleDateFormat.parse("01/01/1982");
		MedicalrecordModel listMedicalRecord1 = new MedicalrecordModel("firstName3", "lastName2", birthday, medications, allergies);
		listMedicalRecord.add(listMedicalRecord1);
		
		dataDao.upDateData();
		//vérification de maj des données
		assertEquals( pageData.getMedicalrecords().size(), 6);
		//vérification que l'age est bien calculé
		assertEquals( pageData.getMedicalrecords().get(5).getAge(), 39);

				
	}

}
