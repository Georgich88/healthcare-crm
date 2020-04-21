package domain.facilities;

import domain.schedule.Plannable;

import java.time.LocalDateTime;


/**
 * Rooms and spaces for hospital's appointments.
 */
public class Room implements Plannable {

    private Long id;
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public boolean isAvailable(LocalDateTime begin, LocalDateTime end) {
        return false;
    }

}
