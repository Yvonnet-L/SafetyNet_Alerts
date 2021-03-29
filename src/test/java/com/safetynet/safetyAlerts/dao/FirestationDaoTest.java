package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.model.FirestationModel;

@SpringBootTest
public class FirestationDaoTest {


	@Autowired
	FirestationDao firestationDao;
	
	@Test
	public void findAllFirestationTest() throws Exception {
		assertThat(firestationDao.findAll().size()).isEqualTo(13);
	}
	

	@Test
	public void postFirestationTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("stationTest", "address Test");

		int size = firestationDao.findAll().size();
		firestationDao.save(firestationPost);		
		assertThat(firestationDao.findAll().size()).isEqualTo(size+1);
		firestationDao.deleteById(firestationPost);
	}
	
	@Test
	public void putFirestationTest() throws Exception {
		
		FirestationModel firestationPost = new FirestationModel("3","1509 Culver St");		
		assertThat(firestationDao.put(firestationPost).size()).isEqualTo(1);
		
	}
	
	
	@Test
	public void deleteFirestationTest() throws Exception {
		FirestationModel firestationDelete = new FirestationModel("stationDelete", "address Test");
		firestationDao.save(firestationDelete);
		int size = firestationDao.findAll().size();
		firestationDao.deleteById(firestationDelete);
		assertThat(firestationDao.findAll().size()).isEqualTo(size-1);	
	}
}
