package com.isaev.ee.healthcarecrm.unitsofwork;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.isaev.ee.healthcarecrm.dao.facilities.BuildingDao;
import com.isaev.ee.healthcarecrm.dao.organizaion.ClinicDao;
import com.isaev.ee.healthcarecrm.dao.people.MedicalStaffMemberDao;
import com.isaev.ee.healthcarecrm.dao.people.MedicalStaffPositionDao;
import com.isaev.ee.healthcarecrm.dao.people.PatientDao;
import com.isaev.ee.healthcarecrm.domain.facilities.Building;
import com.isaev.ee.healthcarecrm.domain.organization.Clinic;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffPosition;
import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.domain.schedule.Timetable;

@SpringBootTest
public class TimetableUnitOfWorkTest {
	
	@Autowired
	TimetableUnitOfWork unitOfWork;
	@Autowired
	ClinicDao clinicDao;
	@Autowired
	BuildingDao buildingDao;
	@Autowired
	MedicalStaffPositionDao medicalStaffPositionDao;	
	@Autowired
	MedicalStaffMemberDao medicalStaffMemberDao;
	@Autowired
	PatientDao patientDao;
	
	@Test
	void shouldProcedeCRUDOperation() {
		
		var clinic = new Clinic("Oak Clinic");
		clinicDao.save(clinic);
		
		var building = new Building("Oakland Main building");
		var room = building.addNewRoom("Main hall");
		buildingDao.save(building);		
		
		MedicalStaffPosition position = new MedicalStaffPosition("doctor in Oak Clinic");
		List<MedicalStaffMember> medicalStaff = List.of(new MedicalStaffMember("Jonh", "Bones", "Jones", position));
		List<Patient> patients = List.of(new Patient("Demetrius", "Mighty Mouse", "Jonson"));
		
		medicalStaffPositionDao.save(position);
		medicalStaffMemberDao.saveAll(medicalStaff);
		patientDao.saveAll(patients);
		
		var timetable = new Timetable();		
		timetable.setClinic(clinic);
		timetable.addSlot("injection", "inject vaccine", "virus antibodies", 
				LocalDateTime.of(2020, 1, 1, 8, 30), LocalDateTime.of(2020, 1, 1, 1, 30), 
				room, medicalStaff, patients);
		timetable.addSlot("injection", "inject vaccine", "virus antibodies", 
				LocalDateTime.of(2020, 2, 1, 8, 30), LocalDateTime.of(2020, 2, 1, 1, 30), 
				room, medicalStaff, patients);
		timetable.calculateDataRangeBySlots();
		
		assertDoesNotThrow(() -> unitOfWork.registerNew(timetable));
		assertDoesNotThrow(unitOfWork::commit);

	}

}
