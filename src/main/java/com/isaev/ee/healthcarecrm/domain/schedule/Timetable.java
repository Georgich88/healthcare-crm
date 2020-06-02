package com.isaev.ee.healthcarecrm.domain.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.isaev.ee.healthcarecrm.domain.facilities.Room;
import com.isaev.ee.healthcarecrm.domain.organization.Clinic;
import com.isaev.ee.healthcarecrm.domain.people.MedicalStaffMember;
import com.isaev.ee.healthcarecrm.domain.people.Patient;

/**
 * Different clinic's timetables for scheduling.
 */
@Entity
@DynamicUpdate
@Table(name = "timetables")
public class Timetable {

    @Id
    private UUID id;
    private String name;
    @Embedded
    private DateRange dateRange;
    @OneToOne
    private Clinic clinic;
    
    @OneToMany
    private List<Slot> slots;

    // Constructors
    
    public Timetable() {
   		this.id = UUID.randomUUID();
        this.name = "";
        this.slots = new ArrayList<Slot>();
        this.dateRange = new DateRange();
    }

    public Timetable(String name, List<Slot> slots) {
    	this.id = UUID.randomUUID();
        this.name = name;
        this.slots = slots;
        this.dateRange = new DateRange();
    }
    
    // Slots methods
    
    public Slot addSlot(String name, String description, String treatment, LocalDateTime startTime, LocalDateTime endTime,
			Room room, List<MedicalStaffMember> medicalStaff, List<Patient> patients) {
    	Slot slot = new Slot(name, description, treatment, startTime, endTime, room,  medicalStaff, patients);
    	this.slots.add(slot);
    	return slot;
    }
    
    public void calculateDataRangeBySlots() {
    	
    	LocalDateTime startTime = null;
    	LocalDateTime endTime = null;
    	
		for (var slot : this.slots) {
			if (startTime != null && startTime.isAfter(slot.getStartTime())
					|| startTime == null && slot.getStartTime() != null) {
				startTime = slot.getStartTime();
			}
			if (endTime != null && endTime.isBefore(slot.getEndTime())
					|| endTime == null && slot.getEndTime() != null) {
				endTime = slot.getEndTime();
			}
    	}
		
		if (startTime != null) 
			this.dateRange.setStartDate(startTime.toLocalDate());
		if (endTime != null) 
			this.dateRange.setEndDate(endTime.toLocalDate());    	
    }

    // Getters and setters
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return this.dateRange.getStartDate();
	}

	public void setStartDate(LocalDate startDate) {
		this.dateRange.setStartDate(startDate);
	}

	public LocalDate getEndDate() {
		return this.dateRange.getEndDate();
	}

	public void setEndDate(LocalDate endDate) {
		this.dateRange.setEndDate(endDate);
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}
    
}
