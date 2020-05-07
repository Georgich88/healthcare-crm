package com.isaev.ee.healthcarecrm.domain.people;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Patients and observed people.
 */
@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "patients")
public class Patient extends Person {
    
	// Constructors
	
    public Patient() {
    }

    public Patient(String firstName, String middleName, String lastName) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
    }

	// Object inherited methods	   
    
    @Override
    public String toString() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }
    

}
