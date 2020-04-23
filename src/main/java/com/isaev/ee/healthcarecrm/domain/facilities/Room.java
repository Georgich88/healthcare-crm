package com.isaev.ee.healthcarecrm.domain.facilities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.isaev.ee.healthcarecrm.domain.schedule.Plannable;


/**
 * Rooms and spaces for hospital's appointments.
 */
@Entity
@Table(name = "rooms")
public class Room implements Plannable {

	@Id
    private UUID id;
    private String name;
     
    // Constructors    
    
    public Room() {
        this.id = UUID.randomUUID();
    }
    
    public Room(String name) {
    	this();
        this.name = name;
    }

    // Getters and setters
      
 	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}      
    
    // Business logic
    
     public boolean isAvailable(LocalDateTime begin, LocalDateTime end) {
        return false;
    }




}
