package com.isaev.ee.healthcarecrm.dao.people;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.isaev.ee.healthcarecrm.domain.people.Patient;

@SpringBootTest
public class PatientDaoTest {
	
	@Autowired
    private PatientDao patientDao;
	
	@Test
	void shouldFindFirstPatientsPage() {

		// Find first 5 patients
		Pageable pageable = PageRequest.of(1, 3);
		List<Patient> patientsFirstNameFiltered = patientDao.findAll(pageable);
		assertEquals(3, patientsFirstNameFiltered.size());

	}

}
