package com.safetynet.safetyAlerts;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetyAlerts.dao.FirestationDao;
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
	
	
}
