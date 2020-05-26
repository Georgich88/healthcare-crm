package com.isaev.ee.healthcarecrm.identitymaps.people;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.isaev.ee.healthcarecrm.dao.people.PatientDao;
import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.identitymaps.people.PatientIdentityMap;
import com.isaev.ee.healthcarecrm.selectionfactories.people.PatientSelectionCriteria;
import com.isaev.ee.healthcarecrm.selectionfactories.people.PatientSelectionFactory;

@SpringBootTest
public class PatientFinderTest {

	@Autowired
	private PatientFinder finder;
	@Autowired
	private PatientDao patientDao;
	
	
	@Test
	void shouldFindPatientsById() {

		UUID id = UUID.randomUUID();
		
		var patient = new Patient("Jon", "Bone", "Jones");
		patient.setId(id);
		patientDao.save(patient);
		
		var firstFoundPatient = finder.findById(id);
		var secondFoundPatient = finder.findById(id);
		
		assertTrue(firstFoundPatient == secondFoundPatient);
		
	}
	
}
