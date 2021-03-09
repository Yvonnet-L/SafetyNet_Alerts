package com.safetynet.safetyAlerts.controller;



import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetyAlerts.dao.MedicalRecordDao;
import com.safetynet.safetyAlerts.model.MedicalrecordModel;
import com.safetynet.safetyAlerts.service.MedicalRecordService;

	@AutoConfigureMockMvc
	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class MedicalrecordControllerTest {

		@Autowired
		private MockMvc mockMvc;

		@Autowired
		private ObjectMapper objectMapper;

		@Autowired
		MedicalRecordDao medicalrecordDao;

		@Autowired
		MedicalRecordService medicalRecordService;

		@Test
		public void getMedicalrecordsTest() throws Exception {		
			mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk());	
		}

		@Test
		public void getMedicalrecordByFirstNameAndLastNameTest() throws Exception {
			mockMvc.perform(get("/medicalrecord/John Boyd"))
					.andExpect(status().isOk()).andExpect(jsonPath("$[0].birthdate", is("02/01/1984")));
		}

		@Test
		public void getMedicalrecordByIdWithEspaceTest() throws Exception {
			mockMvc.perform(get("/medicalrecord/  ")).andExpect(status().isOk());
		}
		
		
		@Test
		public void addMedicalrecordTest() throws Exception {
			List<String> medications = null;
			List<String> allergies = null;
			Date birthday = null; 
			MedicalrecordModel medicalrecordToCreate = new MedicalrecordModel("firstName", "lastName", birthday, medications, allergies);

			mockMvc.perform(post("/medicalrecord/")
					.contentType("application/json")
					.accept(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(medicalrecordToCreate)))
				.andExpect(status().isOk());
		}


		@Test
		public void PutMedicalrecordTest() throws Exception {
			
			List<String> medications = null;
			List<String> allergies = null;		
			Date birthday = null;

		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    String stringBirthday="01/01/2001";
		    birthday = simpleDateFormat.parse(stringBirthday);
			MedicalrecordModel medicalrecordUpDate = new MedicalrecordModel("firstName", "lastName", birthday, medications, allergies);

			mockMvc.perform(put("/medicalrecord/")
					.contentType("application/json")
					.accept(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(medicalrecordUpDate)))
				.andExpect(status().isOk());		
		}

		@Test
		public void deleteMedicalrecordTest() throws Exception {
		
			List<String> medications = null;
			List<String> allergies = null;		
			Date birthday = null;

		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    String stringBirthday="01/01/2001";
		    birthday = simpleDateFormat.parse(stringBirthday);
			MedicalrecordModel medicalrecordDelete = new MedicalrecordModel("firstName", "lastName", birthday, medications, allergies);

			mockMvc.perform(delete("/medicalrecord/")
					.contentType("application/json")
					.accept(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(medicalrecordDelete)))
				.andExpect(status().isOk());		
		}

	
}
