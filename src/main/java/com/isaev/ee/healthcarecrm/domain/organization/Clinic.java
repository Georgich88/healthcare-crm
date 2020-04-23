package com.isaev.ee.healthcarecrm.domain.organization;

import java.util.List;
import java.util.UUID;

import com.isaev.ee.healthcarecrm.domain.facilities.Room;

/**
 * Clinics and organizations.
 */
public class Clinic {

    private UUID id;
    private String name;

    private List<Department> departments;

    public Department addNewDepartment(String facultyName) {
        Department faculty = new Department(facultyName);
        this.departments.add(faculty);
        return faculty;
    }
    
    // Constructors
    
   	public Clinic() {
   		this.id = UUID.randomUUID();
   	}
       
   	public Clinic(String name) {
   		this();
   		this.name = name;
   	}
   	
    // Getters and setters
   	
   	public String getName() {
   		return name;
   	}

   	public void setName(String name) {
   		this.name = name;
   	}

   	public List<Department> getDepartments() {
   		return departments;
   	}

}
