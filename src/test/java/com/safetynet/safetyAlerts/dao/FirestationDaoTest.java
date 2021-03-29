package com.safetynet.safetyAlerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.safetyAlerts.exceptions.DataExistException;
import com.safetynet.safetyAlerts.exceptions.DataNotFoundException;
import com.safetynet.safetyAlerts.model.FirestationModel;
import com.safetynet.safetyAlerts.model.PersonModel;

@ExtendWith(MockitoExtension.class)
public class FirestationDaoTest {

	@InjectMocks
	FirestationDaoImpl firestationDao;
	

	private List<FirestationModel> firestations = new ArrayList<>();

	private List<PersonModel> persons = new ArrayList<>();
	
	@BeforeEach
    public void setUp() {
	
    	FirestationModel firestation1 = new FirestationModel("fire1","address1");
    	FirestationModel firestation2 = new FirestationModel("fire2","address2");
    	FirestationModel firestation3 = new FirestationModel("fire2","address3");
    	FirestationModel firestation4 = new FirestationModel("fire3","address4");	
        firestations.add(firestation1);
		firestations.add(firestation2);
		firestations.add(firestation3);
		firestations.add(firestation4);
		
		firestationDao.setAllFireStations(firestations);
		
		PersonModel person1 = new PersonModel("firstName1","LastName1","address1","city",95000," 06 06 06 06", "mail@mail.com",18);
		PersonModel person2 = new PersonModel("firstName2","LastName2","address2","city",95000," 06 06 06 06", "mail@mail.com",18);
		persons.add(person1);
		persons.add(person2);
		
		firestationDao.setAllPersons(persons);
	}
	
	@Test
	public void findAllFirestationTest() throws Exception {
		assertThat(firestationDao.findAll().size()).isEqualTo(4);
		assertEquals(persons.get(1).getFirestation(), null);
	}
	
	@Test
	public void saveFirestationTest() throws Exception {
		FirestationModel firestationPost = new FirestationModel("stationTest", "address Test");

		int size = firestationDao.findAll().size();
		
		assertThat(firestationDao.save(firestationPost)).isNotNull();		
		assertThat(firestationDao.findAll().size()).isEqualTo(size+1);
		assertEquals(persons.get(1).getFirestation(), "fire2");
		assertThrows(DataExistException.class, () -> firestationDao.save(firestationPost));
	}
	
	
	@Test
	public void putFirestationTest() throws Exception {		
		FirestationModel firestationPost = new FirestationModel("station5","address2");		
		assertThat(firestationDao.put(firestationPost).size()).isEqualTo(1);
		assertEquals(persons.get(1).getFirestation(), "station5");
		
		FirestationModel firestationP = new FirestationModel("fireNotExist","addressNotExist"); 
		assertThrows(DataNotFoundException.class, () -> firestationDao.put(firestationP));
	}
	 
	@Test
	public void deleteByIdFirestationTest() throws Exception {
		
		FirestationModel firestation4 = new FirestationModel("fire3","address4");
		int size = firestationDao.findAll().size();
		
		assertThat(firestationDao.deleteById(firestation4).size()).isEqualTo(1);
		assertThat(firestationDao.findAll().size()).isEqualTo(size-1);
		assertThrows(DataNotFoundException.class, () -> firestationDao.deleteById(firestation4));
	}


}