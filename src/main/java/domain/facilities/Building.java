package domain.facilities;

import java.util.List;


/**
 * Buildings and other real estate assets.
 */
public class Building {

    private Long id;
    private String name;

    private List<Room> rooms;

    public Room addNewRoom(String roomName) {
        Room room = new Room(roomName);
        this.rooms.add(room);
        return room;
    }

}
