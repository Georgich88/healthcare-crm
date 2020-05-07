package com.isaev.ee.healthcarecrm.domain.schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

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
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @OneToOne
    private Room room;   
     
    @ManyToMany
    private List<MedicalStaffMember> medicalStaff;
    @ManyToMany
    private List<Patient> patients;

    // Constructors
    
   	public Slot() {
   		this.id = UUID.randomUUID();
   	}
       
   	public Slot(String name) {
   		this();
   		this.name = name;
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
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
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
