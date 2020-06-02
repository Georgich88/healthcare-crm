package com.isaev.ee.healthcarecrm.domain.people;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Medical staff: doctors, nurses, etc.
 */
@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "medical_staff_members")
public class MedicalStaffMember extends Person {

	@OneToOne
    private MedicalStaffPosition position;

    // Constructors
	
    public MedicalStaffMember() {
        this("","","", null);
    }	
	
    public MedicalStaffMember(String firstName, String middleName, String lastName, MedicalStaffPosition position) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.position = position;
    }

	// Object inherited methods	
    
	@Override
	public String toString() {
		return String.format(
				"MedicalStaffMember [getFirstName()=%s, getMiddleName()=%s, getLastName()=%s, position=%s]",
				getFirstName(), getMiddleName(), getLastName(), position);
	}
    
    

}
