package com.safetynet.safetyAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.model.FirestationModel;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationServiceTest {

	@Autowired
	public MockMvc mockMvc;

	@Autowired
	FirestationDao firestationDao;

	@Autowired
	FirestationService firestationService;

	@Test
	public void getFirestationTest() throws Exception {

		// GIVEN
		List<FirestationModel> firestationsList = new ArrayList<FirestationModel>();
		// WHEN
		// THEN
		assertThat(firestationService.getFirestations().size()).isEqualTo(12);
	}

	@Test
	public void findFirestationByIdTest() throws Exception {

		// GIVEN
		List<FirestationModel> firestationsList = new ArrayList<FirestationModel>();
		// WHEN
		// THEN
		assertThat(firestationService.findById("3").size()).isEqualTo(5);
	}

	@Test
	public void deleteFirestationByIdTest() throws Exception {

		// GIVEN
		FirestationModel firestationDelete = new FirestationModel("", "908 73rd St");
		FirestationModel firestationDeleteNull = null;
		// WHEN
		// THEN
		assertThat(firestationService.delete(firestationDelete).size()).isEqualTo(1);
		//assertThat(firestationService.delete(firestationDeleteNull).size()).isEqualTo(0);
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

}
