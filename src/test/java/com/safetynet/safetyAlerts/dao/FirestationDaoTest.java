package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetyAlerts.model.FirestationModel;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationDaoTest {

	@Autowired
	FirestationDao firestationDao;

	@Test
	public void findAllFirestationTest() throws Exception {
		Thread.sleep(1000);
		assertThat(firestationDao.findAll().size()).isEqualTo(15);
	}
	
	@Test
	public void findFirestationByIdTest() throws Exception {
		assertThat(firestationDao.findById("3").size()).isEqualTo(5);
		assertThat(firestationDao.findById(" ").size()).isEqualTo(0);
		assertThat(firestationDao.findById("").size()).isEqualTo(0);
	}
	
	@Test
	public void postFirestationTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("stationTest", "address");
		assertThat(firestationDao.save(firestationPost));	
	}
	

	@Test
	public void postFirestationWithSpaceTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("", "");
		assertThat(firestationDao.save(firestationPost));	
	}
	
	@Test
	public void putFirestationTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("stationTest", "address Test");
		assertThat(firestationDao.put(firestationPost).add(firestationPost));	
	}
	
	@Test
	public void putFirestationWithSpaceTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("", "");
		assertThat(firestationDao.put(firestationPost).isEmpty());	
	}
	
	@Test
	public void deleteFirestationTest() throws Exception {
		FirestationModel firestationDelete = new FirestationModel("stationtest", "address Test");
		assertThat(firestationDao.deleteById(firestationDelete).size()).isEqualTo(0);
	}
	
	@Test
	public void deleteFirestationWithJsonNullErrorTest() throws Exception {
		FirestationModel firestationDelete = new FirestationModel("", "");
		assertThat(firestationDao.deleteById(firestationDelete).size()).isEqualTo(0);
	}

}
