package com.isaev.ee.healthcarecrm.identitymaps.people;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.people.PatientDao;
import com.isaev.ee.healthcarecrm.domain.people.Patient;

@Component
public class PatientFinder {
	
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private PatientIdentityMap identityMap;
	
	public Patient findById(UUID id) {
		var patient = identityMap.get(id);
		if (patient == null) {
			patient = patientDao.findById(id).orElseThrow();
			identityMap.add(patient);
		}
		return patient;
	}

}
