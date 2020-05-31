package com.isaev.ee.healthcarecrm.identitymaps.people;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.people.PatientDao;
import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.identitymaps.IdentityMap;

@Component
public class PatientIdentityMap implements IdentityMap<UUID, Patient> {

	private static Map<UUID, Patient> patients = new ConcurrentHashMap<>();
	@Autowired
	private PatientDao patientDao;
	
	@Override
	public void add(Patient patient) {
		patientDao.save(patient);
		patients.putIfAbsent(patient.getId(), patient);
		
	}
	
	@Override
	public void add(UUID id, Patient patient) {
		patientDao.save(patient);
		patients.putIfAbsent(id, patient);
		
	}

	@Override
	public Patient get(UUID key) {
		patients.putIfAbsent(key, patientDao.findById(key).orElseThrow());
		return patients.get(key);
	}

}
