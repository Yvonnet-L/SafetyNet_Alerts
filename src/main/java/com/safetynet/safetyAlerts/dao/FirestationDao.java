package com.safetynet.safetyAlerts.dao;

import java.util.List;

import com.safetynet.safetyAlerts.model.FirestationModel;



public interface FirestationDao {
	
	public List<FirestationModel> findAll();
	
	public List<FirestationModel> findById(String station);
	
	public FirestationModel save(FirestationModel firestation);
	
	public List<FirestationModel> deleteById(FirestationModel firestation);

	public void setAllFireStations(List<FirestationModel> listFireStation);

	public List<FirestationModel> put(FirestationModel firestation);
}
