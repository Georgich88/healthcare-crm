package com.isaev.ee.healthcarecrm.domain.schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.datetime.standard.DateTimeContext;

import com.isaev.ee.healthcarecrm.domain.facilities.Room;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;
import com.isaev.ee.healthcarecrm.domain.people.Patient;

/**
 * Time slots for medical treatments appointments.
 */
@Entity
@DynamicUpdate
@Table(name = "slots")
public class Slot {

	@Id
    private UUID id;
    private String name;
    private String description;
    private String treatment;
    @Embedded
    private DateTimeRange dataTimeRange;
    @OneToOne
    private Room room;   
     
    @ManyToMany
    private List<MedicalStaffMember> medicalStaff;
    @ManyToMany
    private List<Patient> patients;

    // Constructors
    
   	public Slot() {
   		this.id = UUID.randomUUID();
   		this.dataTimeRange = new DateTimeRange();
   	}
       
   	public Slot(String name) {
   		this();
   		this.name = name;
   	}
   	
   	
   	public Slot(String name, String description, String treatment, LocalDateTime startTime, LocalDateTime endTime,
			Room room, List<MedicalStaffMember> medicalStaff, List<Patient> patients) {
		this();
   		this.name = name;
		this.description = description;
		this.treatment = treatment;
		this.dataTimeRange.setStartTime(startTime);
		this.dataTimeRange.setEndTime(endTime);
		this.room = room;
		this.medicalStaff = medicalStaff;
		this.patients = patients;
	}

	// Getters and setters
   	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public LocalDateTime getStartTime() {
		return this.dataTimeRange.getStartTime();
	}

	public void setStartTime(LocalDateTime startTime) {
		this.dataTimeRange.setStartTime(startTime);
	}

	public LocalDateTime getEndTime() {
		return this.dataTimeRange.getEndTime();
	}

	public void setEndTime(LocalDateTime endTime) {
		this.dataTimeRange.setEndTime(endTime);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<MedicalStaffMember> getMedicalStaff() {
		return medicalStaff;
	}

	public void setMedicalStaff(List<MedicalStaffMember> medicalStaff) {
		this.medicalStaff = medicalStaff;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}	

}
