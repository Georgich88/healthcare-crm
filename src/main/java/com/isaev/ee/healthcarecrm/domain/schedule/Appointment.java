package com.isaev.ee.healthcarecrm.domain.schedule;

import java.time.LocalDateTime;
import java.util.List;

import com.isaev.ee.healthcarecrm.domain.facilities.Room;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;
import com.isaev.ee.healthcarecrm.domain.people.Patient;
import com.isaev.ee.healthcarecrm.domain.treatment.Treatment;

/**
 * Time slots for medical treatments appointments.
 */
public class Appointment {

    private Long id;

    private List<MedicalStaffMember> medicalStaff;
    private List<Patient> patients;
    private Room room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private String description;
    private Treatment treatment;

    public Appointment() {
    }

}
