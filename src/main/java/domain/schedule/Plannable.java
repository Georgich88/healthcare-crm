package domain.schedule;

import java.time.LocalDateTime;

public interface Plannable {

    public boolean isAvailable(LocalDateTime begin, LocalDateTime end);

}
