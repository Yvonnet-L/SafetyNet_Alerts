package com.safetynet.safetyAlerts.dao;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataDaoTest {

	@InjectMocks
	DataDaoImpl dataDao;
	
	@Test
	public void initMapperTest() throws IOException {
		
		dataDao.initMapper();
		
	}
	
	
}
