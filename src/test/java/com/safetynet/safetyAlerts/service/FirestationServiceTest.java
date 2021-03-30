package com.safetynet.safetyAlerts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetyAlerts.dao.FirestationDao;
import com.safetynet.safetyAlerts.exceptions.DataNotConformException;
import com.safetynet.safetyAlerts.model.FirestationModel;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

	@InjectMocks
	FirestationService firestationService;
	
	@Mock
	FirestationDao firestationDao;
	
	private List<FirestationModel> firestations = new ArrayList<>();

	private FirestationModel firestationNotConform1 = new FirestationModel(null, null);
	private FirestationModel firestationNotConform2 = new FirestationModel(null, "address1");
	private FirestationModel firestationNotConform3 = new FirestationModel("?##", "address1");
	private FirestationModel firestationNotConform4 = new FirestationModel("station1", "?##");

	@Test
	public void getFirestationTest() throws Exception {
		
		// GIVEN
		firestations.add(new FirestationModel("station1","address1"));
		firestations.add(new FirestationModel("station2","address2"));
		// WHEN
		Mockito.when(firestationDao.findAll()).thenReturn(firestations);
		// THEN
		assertThat(firestationService.getFirestations().size()).isEqualTo(2);
	}

	@Test
	public void putFirestationByAddressTest() throws Exception {

		// GIVEN
		FirestationModel firestationPut = new FirestationModel("station2","address1");
		firestations.add(firestationPut);
		// WHEN
		Mockito.when(firestationDao.put(firestationPut)).thenReturn(firestations);
		// THEN
		assertThat(firestationService.updateFirestation(firestationPut).size()).isEqualTo(1);
	}
	

	@Test
	public void addFirestationByAddressTest() throws Exception {

		// GIVEN
		FirestationModel firestationAdd = new FirestationModel("station2","address1");
		// WHEN
		Mockito.when(firestationDao.save(firestationAdd)).thenReturn(firestationAdd);
		// THEN
		assertFalse(firestationService.addFirestation(firestationAdd).toString().isEmpty());
	}
	
	@Test
	public void deleteFirestationWithStationAndAdressTest() throws Exception {

		// GIVEN
		firestations.add(new FirestationModel("33", "at home2"));
		firestations.add(new FirestationModel("33", "at home1"));
		firestations.add(new FirestationModel("3", "at home3"));
			
		FirestationModel firestationDelete = new FirestationModel("33", "at home3");
		// WHEN		
		Mockito.when(firestationDao.deleteById(firestationDelete)).thenReturn(firestations);
		// THEN 4
		assertThat(firestationService.delete(firestationDelete).size()).isEqualTo(3);
		
	}
	
	@Test
	public void addFirestationWithStationNotConformTest() throws Exception {
		
		assertThrows(DataNotConformException.class, () -> firestationService.addFirestation(firestationNotConform1));
		assertThrows(DataNotConformException.class, () -> firestationService.addFirestation(firestationNotConform2));
		assertThrows(DataNotConformException.class, () -> firestationService.addFirestation(firestationNotConform3));
		assertThrows(DataNotConformException.class, () -> firestationService.addFirestation(firestationNotConform4));
	}
	
	@Test
	public void deleteFirestationWithStationNotConformTest() throws Exception {
		
		assertThrows(DataNotConformException.class, () -> firestationService.delete(firestationNotConform1));
		assertThrows(DataNotConformException.class, () -> firestationService.delete(firestationNotConform2));
		assertThrows(DataNotConformException.class, () -> firestationService.delete(firestationNotConform3));
		assertThrows(DataNotConformException.class, () -> firestationService.delete(firestationNotConform4));
	}
	
	@Test
	public void updateFirestationWithStationNotConformTestTest() throws Exception {
		
		assertThrows(DataNotConformException.class, () -> firestationService.updateFirestation(firestationNotConform1));
		assertThrows(DataNotConformException.class, () -> firestationService.updateFirestation(firestationNotConform2));
		assertThrows(DataNotConformException.class, () -> firestationService.updateFirestation(firestationNotConform3));
		assertThrows(DataNotConformException.class, () -> firestationService.updateFirestation(firestationNotConform4));	
	}

}
