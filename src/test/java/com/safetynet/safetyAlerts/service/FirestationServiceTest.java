package com.safetynet.safetyAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.FirestationModel;

@SpringBootTest
public class FirestationServiceTest {

	//@Autowired
	//public MockMvc mockMvc;

	@Autowired
	FirestationDao firestationDao;

	@Autowired
	FirestationService firestationService;
	


	@Test
	public void getFirestationTest() throws Exception {
		// THEN 13
		assertThat(firestationService.getFirestations().size()).isEqualTo(13);
	}

	
	@Test
	public void postFirestationTest() throws Exception {

		// GIVEN
		FirestationModel firestationPost = new FirestationModel("66", "at home");
		// WHEN
		// THEN
		assertThat(firestationService.addFirestation(firestationPost));
	}

	@Test
	public void putFirestationByAddressTest() throws Exception {

		// GIVEN
		FirestationModel firestationPut = new FirestationModel("1", "644 Gershwin Cir");
		// WHEN
		// THEN
		assertThat(firestationService.updateFirestation(firestationPut).size()).isEqualTo(1);
	}
	

	@Test
	public void deleteFirestationWithStationAndAdressTest() throws Exception {

		// GIVEN
		FirestationModel firestationAdd1 = new FirestationModel("33", "at home2");
		FirestationModel firestationAdd2 = new FirestationModel("33", "at home1");
		FirestationModel firestationAdd3 = new FirestationModel("3", "at home3");
		firestationService.addFirestation(firestationAdd1);
		firestationService.addFirestation(firestationAdd2);
		firestationService.addFirestation(firestationAdd3);
		FirestationModel firestationDelete = new FirestationModel("33", "at home3");
		// WHEN
		// THEN 4
		assertThat(firestationService.delete(firestationDelete).size()).isEqualTo(3);
		
	}
	
	@Test
	public void deleteFirestationWithStationNotexistTest() throws Exception {

		FirestationModel firestationDelete = new FirestationModel("stationNotExist", "addressNotExist");
		assertThrows(DataNotFoundException.class, () -> firestationService.delete(firestationDelete));
	}
	
	@Test
	public void updateFirestationWithAddressNotexistTest() throws Exception {
			
		FirestationModel firestationPut =  new FirestationModel("stationNotExist", "addressNotExist");;
		assertThrows(DataNotFoundException.class, () -> firestationService.updateFirestation(firestationPut));	
	}

}
