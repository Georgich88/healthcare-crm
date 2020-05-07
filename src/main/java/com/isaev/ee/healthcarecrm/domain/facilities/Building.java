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

    // Business logic
    
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

	// Object inherited methods	
	
	@Override
	public String toString() {
		return String.format("Building [id=%s, name=%s, rooms=%s]", id, name, rooms);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Building other = (Building) obj;
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
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		return true;
	}
	
	
		
     
}
