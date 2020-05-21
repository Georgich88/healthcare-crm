package com.isaev.ee.healthcarecrm.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.isaev.ee.healthcarecrm.dao.facilities.BuildingDao;
import com.isaev.ee.healthcarecrm.dao.facilities.RoomDao;
import com.isaev.ee.healthcarecrm.dao.organizaion.ClinicDao;
import com.isaev.ee.healthcarecrm.dao.people.MedicalStaffMemberDao;
import com.isaev.ee.healthcarecrm.dao.people.MedicalStaffPositionDao;
import com.isaev.ee.healthcarecrm.dao.people.PatientDao;
import com.isaev.ee.healthcarecrm.dao.schedule.SlotDao;
import com.isaev.ee.healthcarecrm.dao.schedule.TimetableDao;
import com.isaev.ee.healthcarecrm.demodatageneration.DataGenerator;
import com.isaev.ee.healthcarecrm.domain.facilities.Building;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffPosition;
import com.isaev.ee.healthcarecrm.domain.people.Patient;

@Component
public class DatabaseLoader implements CommandLineRunner {

	@Autowired
    private BuildingDao buildingDao;
	@Autowired
    private MedicalStaffMemberDao medicalStaffMemberDao;	
	@Autowired
    private MedicalStaffPositionDao medicalStaffPositionDao;	
	@Autowired
    private PatientDao patientDao;	
	
    @Override
    public void run(String... args) throws Exception {

        Building mainBuilding = DataGenerator.generateDemoBuilding();        
        this.buildingDao.save(mainBuilding);
        
        MedicalStaffPosition doctorPosition = DataGenerator.generateDoctorPosition();
        medicalStaffPositionDao.save(doctorPosition);
        
        List<MedicalStaffMember> doctors = DataGenerator.generateDemoDoctors(doctorPosition);
        doctors.forEach(medicalStaffMemberDao::save);
        
        List<Patient> patients = DataGenerator.generateDemoBeautyClinicPatients();
        patientDao.saveAll(patients);
    }

}
