package com.safetynet.safetyAlerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

	MedicalrecordModel medicalrecord;
	List<String> medications = new ArrayList<String>();
	List<String> allergies = new ArrayList<String>();
	Date birthday = null;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@BeforeEach
	public void init() throws Exception {
		String stringBirthday = "01/01/2001";
		birthday = simpleDateFormat.parse(stringBirthday);
		medicalrecord = new MedicalrecordModel("firstNameTestC", "lastNameTestC", birthday, medications, allergies);
	}

	@Test
	@DisplayName("Test sur la recherche des medicalrecords")
	public void getMedicalrecordsTest() throws Exception {
		mockMvc.perform(get("/medicalrecords"))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test sur la recherche des medicalrecords par nom et prénom")
	public void getMedicalrecordByFirstNameAndLastNameTest() throws Exception {
		mockMvc.perform(get("/medicalrecord/John Boyd"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].birthdate", is("02/01/1984")));
	}

	@Test
	@DisplayName("Test sur la recherche de medicalrecord avec un blanc")
	public void getMedicalrecordByIdWithEspaceTest() throws Exception {
		mockMvc.perform(get("/medicalrecord/  "))
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Test sur l'ajout d'un medicalrecord")
	public void addMedicalrecordTest() throws Exception {

		mockMvc.perform(post("/medicalrecord/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(medicalrecord)))
			.andExpect(status().isCreated());

		medicalrecordDao.delete(medicalrecord);
	}

	@Test
	@DisplayName("Test sur l'ajout d'un medicalrecord null")
	public void addMedicalrecordWithNullTest() throws Exception {

		mockMvc.perform(post("/medicalrecord/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(null)))
			.andExpect(status().isBadRequest());

	}
	
	@Test
	@DisplayName("Test sur l'upDate d'un medicalrecord")
	public void PutMedicalrecordTest() throws Exception {

		medicalrecordDao.save(medicalrecord);

		mockMvc.perform(put("/medicalrecord/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(medicalrecord)))
			.andExpect(status().isCreated());

		medicalrecordDao.delete(medicalrecord);
	}

	@Test
	@DisplayName("Test sur la suppression d'un medicalrecord")
	public void deleteMedicalrecordTest() throws Exception {

		medicalrecordDao.save(medicalrecord);

		mockMvc.perform(delete("/medicalrecord/")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(medicalrecord)))
			.andExpect(status().isOk());
	}

}
