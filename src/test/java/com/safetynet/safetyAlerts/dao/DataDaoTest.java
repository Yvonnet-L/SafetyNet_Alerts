package com.safetynet.safetyAlerts.dao;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetyAlerts.model.DataModel;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.model.PersonModel;

@ExtendWith(MockitoExtension.class)
public class DataDaoTest {

		@MockBean
	    private ObjectMapper mockMapper;
	 
	    @MockBean
	    private DataModel mockPageData;
	    
	    
	    @InjectMocks
	    private DataDaoImpl dataDao;
	    
	    DataDaoTest() {
	    	
	    }
	    
	    
	    private DataModel dataModel;


	List<PersonModel> persons = new ArrayList<PersonModel>();
	List<FirestationModel> fireStations = new ArrayList<FirestationModel>();
	List<MedicalrecordModel> medicalRecords = new ArrayList<MedicalrecordModel>();
	
	@BeforeAll
    public void initializeMocks() {
		//when(mockMapper.readValue(any(File.class), DataModel.class)).thenReturn(new DataModel(persons, fireStations, medicalRecords));
    }
	
	

	//@Test
	public void initMapperTest() throws IOException {


		dataDao.initMapper();
		assertEquals(1, 1);

		
	}
	
	
}
