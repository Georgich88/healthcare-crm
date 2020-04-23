package com.isaev.ee.healthcarecrm.domain.facilities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Buildings and other real estate assets.
 */
@Entity
@Table(name = "buildings")
public class Building {

    @Id
    private UUID id;
    private String name;

    @OneToMany
    private List<Room> rooms = new ArrayList<>();

    public Room addNewRoom(String roomName) {
        Room room = new Room(roomName);
        this.rooms.add(room);
        return room;
    }
    
   // Constructors
 
	public Building() {
		this.id = UUID.randomUUID();
	}
    
	public Building(String name) {
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

	public List<Room> getRooms() {
		return rooms;
	}
		
     
}
