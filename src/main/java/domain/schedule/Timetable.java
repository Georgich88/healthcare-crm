package domain.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Different clinic's timetables for scheduling.
 */
public class Timetable {

    private Long id;
    private String name;
    private List<Appointment> slots;
    private LocalDate startDate;
    private LocalDate endDate;

    public Timetable() {
        this.name = "";
        this.slots = new ArrayList<Appointment>();
    }

    public Timetable(String name, List<Appointment> slots) {
        this.name = name;
        this.slots = slots;
    }

}
