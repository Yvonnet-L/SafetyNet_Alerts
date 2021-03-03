package com.safetynet.safetyAlerts.dao;

import java.util.List;

import com.safetynet.safetyAlerts.model.FirestationModel;

public interface FirestationDao {
	
	public List<FirestationModel> findAll();
	
	public List<FirestationModel> findById(String id);
	
	public FirestationModel save(FirestationModel firestation);
	
	public List<FirestationModel> delete(FirestationModel firestation);

	public void setAllFireStations(List<FirestationModel> listFireStation);

	FirestationModel put(FirestationModel firestation);
}
