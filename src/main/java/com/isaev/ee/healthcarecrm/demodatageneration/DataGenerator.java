package com.isaev.ee.healthcarecrm.demodatageneration;

import java.util.List;
import com.isaev.ee.healthcarecrm.domain.facilities.Building;
import com.isaev.ee.healthcarecrm.domain.organization.Clinic;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffPosition;
import com.isaev.ee.healthcarecrm.domain.people.Patient;


public class DataGenerator {

    public static Clinic generateDemoClinic() {

        Clinic beautyClinic = new Clinic("‎beauty clinic");
        return beautyClinic;
    }	
	
    public static Building generateDemoBuilding() {
    	
        Building mainBuilding = new Building("Main building");
        
        mainBuilding.addNewRoom("F1 - 101");
        mainBuilding.addNewRoom("F1 - 102");
        mainBuilding.addNewRoom("F2 - 201");
        mainBuilding.addNewRoom("F2 - 202");
        mainBuilding.addNewRoom("F2 - 203");
        
        return mainBuilding;
        
    }
    
    public static MedicalStaffPosition generateDoctorPosition() {
    	return new MedicalStaffPosition("Doctor");
    }
    
    public static List<MedicalStaffMember> generateDemoDoctors(MedicalStaffPosition position) {
        return List.of(new MedicalStaffMember("Dana", "Jon", "White", position), 
        		new MedicalStaffMember("Herb", "", "Dean", position));
    }
    
    public static List<Patient> generateDemoBeautyClinicPatients() {
        List<Patient> beautyPatients = List.of(new Patient("Mike", "Iron", "Tyson"), 
        		new Patient("Oscar", "De La", "Hoya"),
                new Patient("Floyd", "Joy", "Mayweather"), 
                new Patient("Evander ", "", "Holyfield "),
                new Patient("George", "Edward", "Foreman"), 
                new Patient("William", "Harrison", "Dempsey"),
                new Patient("Joseph", "Louis", "Barrow"), 
                new Patient("Emmanuel", "Dapidran", "Pacquiao"));
        return beautyPatients;
    }    

}
