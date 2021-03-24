package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.exceptions.DataExistException;
import com.safetynet.safetyAlerts.model.FirestationModel;

@SpringBootTest
public class FirestationDaoTest {


	@Autowired
	FirestationDao firestationDao;
	
	FirestationModel firestationPost = new FirestationModel("stationTest", "address Test");
	List<FirestationModel> listFireStation = new ArrayList<>();
	
	@BeforeEach
	private void init() {
		FirestationModel firestation1 = new FirestationModel("Station1","address1");
		FirestationModel firestation2 = new FirestationModel("Station2", "address2");
		FirestationModel firestation3 = new FirestationModel("Station3", "address3");
		FirestationModel firestation4 = new FirestationModel("Station4", "address4");
		List<FirestationModel> listFireStationMock = new ArrayList<>();
		listFireStationMock.add(firestation1);
		listFireStationMock.add(firestation2);
		listFireStationMock.add(firestation3);
		listFireStationMock.add(firestation4);
		
		firestationDao.setAllFireStations(listFireStationMock);
	}

	@Test
	public void findAllFirestationTest() throws Exception {

		assertThat(firestationDao.findAll().size()).isEqualTo(4);
	}
	

	@Test
	public void postFirestationTest() throws Exception {

		int size = firestationDao.findAll().size();
		firestationDao.save(firestationPost);
		
		assertThat(firestationDao.findAll().size()).isEqualTo(size+1);	
	}
	

	@Test
	public void postFirestationWithSpaceTest() throws Exception {
		
		FirestationModel firestationSpace = new FirestationModel(" ", " ");
		firestationDao.save(firestationSpace);	
		assertThrows(DataExistException.class, () -> firestationDao.save(firestationSpace));

	}
	
	@Test
	public void putFirestationTest() throws Exception {
		
		FirestationModel firestationPost = new FirestationModel("Station3", "address1");		
		assertThat(firestationDao.put(firestationPost).size()).isEqualTo(1);
		
	}
	
	
	@Test
	public void deleteFirestationTest() throws Exception {
		FirestationModel firestationDelete = new FirestationModel("stationDelete", "address Delete");
		firestationDao.save(firestationDelete);
		int size = firestationDao.findAll().size();
		firestationDao.deleteById(firestationDelete);
		assertThat(firestationDao.findAll().size()).isEqualTo(size-1);	
	}
}
