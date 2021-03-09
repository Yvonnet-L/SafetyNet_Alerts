package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetyAlerts.model.FirestationModel;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationDaoTest {

	


	@Autowired
	FirestationDao firestationDao;

	


	@Test
	public void findAllFirestationTest() throws Exception {
		assertThat(firestationDao.findAll().size()).isEqualTo(13);
	}
	
	@Test
	public void findFirestationByIdTest() throws Exception {
		assertThat(firestationDao.findById("3").size()).isEqualTo(5);
		assertThat(firestationDao.findById(" ").size()).isEqualTo(0);
		assertThat(firestationDao.findById("").size()).isEqualTo(0);
	}
	
	@Test
	public void postFirestationTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("66", "at home");
		assertThat(firestationDao.put(firestationPost).add(firestationPost));	
	}
	

	@Test
	public void postFirestationWithSpaceTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("", "");
		assertThat(firestationDao.put(firestationPost).isEmpty());	
	}
	
	@Test
	public void deleteFirestationWithJsonNullErrorTest() throws Exception {
		FirestationModel firestationDelete = new FirestationModel("", "");
		assertThat(firestationDao.deleteById(firestationDelete).size()).isEqualTo(0);
	}

}
