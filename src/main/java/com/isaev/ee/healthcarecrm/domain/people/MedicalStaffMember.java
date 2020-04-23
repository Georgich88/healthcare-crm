package com.isaev.ee.healthcarecrm.domain.people;

/**
 * Medical staff: doctors, nurses, etc.
 */
public class MedicalStaffMember extends Person {

    private MedicalStaffPosition position;

    public MedicalStaffMember(String firstName, String middleName, String lastName, MedicalStaffPosition position) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setLastName(lastName);
        this.position = position;
    }

}
