package com.isaev.ee.healthcarecrm.domain.people;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Medical staff ranges: doctors, nurses, etc.
 */
@Entity
@DynamicUpdate
@Table(name = "medical_staff_positions")
public class MedicalStaffPosition {

    @Id
    private UUID id;
    private String name;

    // Constructors
    
 	public MedicalStaffPosition() {
		this.id = UUID.randomUUID();
	}   
    
	public MedicalStaffPosition(String name) {
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
	
	// Object inherited methods	
	
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalStaffPosition other = (MedicalStaffPosition) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
}
