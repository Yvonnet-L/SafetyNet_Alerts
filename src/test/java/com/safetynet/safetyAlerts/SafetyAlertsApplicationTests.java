package com.safetynet.safetyAlerts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.service.AgeCalculService;
import com.safetynet.safetyAlerts.service.FirestationService;

@SpringBootTest
@AutoConfigureMockMvc
class SafetyAlertsApplicationTests {

	@Autowired
	public MockMvc mockMvc;
	
	 @Autowired
	 FirestationDao firestationDao;
	 
	 @Autowired
	 FirestationService firestationService;

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {
		SafetyAlertsApplication.main(new String[] {});
	}

	@Test
	public void getFirestationsTest() throws Exception {

		mockMvc.perform(get("/firestations")).andExpect(status().isOk()).andExpect(jsonPath("$[0].station", is("3")));
	}

	@Test
	public void getFirestationByIdTest() throws Exception {

		mockMvc.perform(get("/firestation/1")).andExpect(status().isOk()).andExpect(jsonPath("$[0].station", is("1")))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].address", is("947 E. Rose Dr")));

		mockMvc.perform(get("/firestation/  ")).andExpect(status().isOk());
	}

	@Test
	public void deleteFirestationByIdTest() throws Exception {
	
        // GIVEN
        FirestationModel firestationDelete = new FirestationModel("", "908 73rd St");
        // WHEN
        // THEN
        assertThat(firestationService.delete(firestationDelete).size()).isEqualTo(1);	     
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
        FirestationModel firestationPut = new FirestationModel("", "644 Gershwin Cir");
        // WHEN
        // THEN
        assertThat(firestationService.updateFirestation(firestationPut).size()).isEqualTo(1);	     
	}
	@Test
	public void ageCalulServiceTest() {
		// GIVEN
		AgeCalculService ageCalcul = new AgeCalculService();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -10);
		Date birthDay = calendar.getTime();		
		int age = 0;
		// WHEN
		// THEN
		assertEquals(ageCalcul.personCalulateAge(birthDay, age), 10);

	}
}
