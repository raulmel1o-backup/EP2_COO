package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MeetingScheduler {

    private LocalDate start;
    private LocalDate end;
    private final List<Guest> guestsList;

    public void scheduleMeetingBetween(LocalDate start, LocalDate end, Collection<String> guestsList) {
        this.start = start;
        this.end = end;

        guestsList.forEach(email -> this.guestsList.add(new Guest(email)));
    }

    public void designateAvailability(String guestEmail, LocalDateTime start, LocalDateTime end) {
        Guest guest = guestsList.stream().filter(g -> g.getEmail().equals(guestEmail)).collect(toList()).get(0);
        guest.addAvailability(start, end);
    }

    public void showOverlapping() {

    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public List<Guest> getGuestsList() {
        return guestsList;
    }

    public MeetingScheduler() {
        this.guestsList = new ArrayList<>();
    }
}
