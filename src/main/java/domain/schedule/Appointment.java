package domain.schedule;

import domain.facilities.Room;
import domain.people.MedicalStaffMember;
import domain.people.Patient;
import domain.treatment.Treatment;

import java.time.LocalDateTime;
import java.util.List;

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
