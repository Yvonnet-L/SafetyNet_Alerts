package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.model.FirestationModel;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationDaoTest {

	/*
	@MockBean
	DataDao dataDao;
	
	@MockBean
	FirestationDao firestationDaoMock;
	*/
	@Autowired
	FirestationDao firestationDao;
	
	FirestationModel firestationPost = new FirestationModel("stationTest", "address Test");


	@Test
	public void findAllFirestationTest() throws Exception {
		//Thread.sleep(1000);
		FirestationModel firestation1 = new FirestationModel("address1","Station1");
		FirestationModel firestation2 = new FirestationModel("address2","Station2");
		FirestationModel firestation3 = new FirestationModel("address3","Station3");
		List<FirestationModel> listFireStationMock = new ArrayList<>();
		listFireStationMock.add(firestation1);
		listFireStationMock.add(firestation2);
		listFireStationMock.add(firestation3);
		//Mockito.when(firestationDaoMock.setAllFireStations(any(List.class)).thenReturn(listFireStationMock));
		assertThat(firestationDao.findAll().size()).isEqualTo(15);

		// setAllFireStations(List<FirestationModel> listFirestation)
	}
	
	


	@Test
	public void postFirestationTest() throws Exception {
		//When
		int size = firestationDao.findAll().size();
		firestationDao.save(firestationPost);
		
		assertThat(firestationDao.findAll().size()).isEqualTo(size+1);	
	}
	

	@Test
	public void postFirestationWithSpaceTest() throws Exception {
		int size = firestationDao.findAll().size();
		FirestationModel firestationPost = new FirestationModel("", "");
		firestationDao.save(firestationPost);
		assertThat(firestationDao.findAll().size()).isEqualTo(size+1);	
	}
	
	@Test
	public void putFirestationTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("stationTest", "address Test");
		assertThat(firestationDao.put(firestationPost).isEmpty());	
	}
	
	@Test
	public void putFirestationWithSpaceTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("", "");
		assertThat(firestationDao.put(firestationPost).isEmpty());	
	}
	
	@Test
	public void deleteFirestationTest() throws Exception {
		FirestationModel firestationDelete = new FirestationModel("stationDelete", "address Delete");
		firestationDao.save(firestationDelete);
		int size = firestationDao.findAll().size();
		firestationDao.deleteById(firestationDelete);
		assertThat(firestationDao.findAll().size()).isEqualTo(size-1);
		
		
	}
	
	@Test
	public void deleteFirestationWithJsonNullErrorTest() throws Exception {
		FirestationModel firestationDelete = new FirestationModel("", "");
		assertThat(firestationDao.deleteById(firestationDelete).size()).isEqualTo(0);
	}

}
