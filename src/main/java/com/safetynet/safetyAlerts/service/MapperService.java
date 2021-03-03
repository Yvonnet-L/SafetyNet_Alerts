package com.safetynet.safetyAlerts.service;

import org.springframework.stereotype.Component;

import com.safetynet.safetyAlerts.dao.DataDao;
import com.safetynet.safetyAlerts.dao.DataDaoImpl;


@Component
public class MapperService {
	
	DataDao dataDao = new DataDaoImpl();
	
		public void InitialisationJson() throws Exception {
			dataDao.InitMapper();
		}

}
