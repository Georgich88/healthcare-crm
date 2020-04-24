package com.isaev.ee.healthcarecrm.domain.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.isaev.ee.healthcarecrm.domain.organization.Clinic;

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
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne
    private Clinic clinic;
    
    @OneToMany
    private List<Slot> slots;

    // Constructors
    
    public Timetable() {
        this.name = "";
        this.slots = new ArrayList<Slot>();
    }

    public Timetable(String name, List<Slot> slots) {
        this.name = name;
        this.slots = slots;
    }

    // Getters and setters
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
