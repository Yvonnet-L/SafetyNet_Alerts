package controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;



@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class FirestationControllerTest {

	@Autowired
	private WebApplicationContext webContext;
	
	@Autowired
	public MockMvc mockMvc;

	
	
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

}
