package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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
        if (start.toLocalDate().isBefore(this.start) || end.toLocalDate().isAfter(this.end)) {
            System.err.println("Invalid dates");
        }

        Guest guest = guestsList.stream().filter(g -> g.getEmail().equals(guestEmail)).collect(toList()).get(0);
        guest.addAvailability(start, end);
    }
  
    public void showOverlapping() {
        final Map<LocalDate, Map<LocalDateTime, List<Guest>>> map = new LinkedHashMap<>();
        LocalDate day = start;

        while (!day.isAfter(end)) {
            final HashMap<LocalDateTime, List<Guest>> mapToAdd = new LinkedHashMap<>();

            for (int i = 0; i < 96; i++) {
                mapToAdd.put(LocalDateTime.of(day, LocalTime.MIDNIGHT.plusMinutes(i * 15)), new ArrayList<>());
            }

            map.put(day, mapToAdd);
            day = day.plusDays(1);
        }

        for (Guest guest : guestsList) {
            final Set<LocalDate> keys = guest.getAvailability().keySet();

            for (LocalDate key : keys) {
                guest.getAvailability().get(key).forEach(timeInterval -> {
                    if (timeInterval.isAvailable()) {
                        map.get(timeInterval.getStart().toLocalDate()).get(timeInterval.getStart()).add(guest);
                    }
                });
            }
        }

        Printer.print(start, end, guestsList, map);
    }

    private List<LocalDate> getSortedOverlappingDates() {
        ListIterator<Guest> guests = guestsList.listIterator();

        return getOverlappingDates(guests, new ArrayList<>())
                .stream()
                .sorted()
                .collect(toList());
    }

    private List<LocalDate> getOverlappingDates(ListIterator<Guest> guests, List<LocalDate> overlappingDates) {
        if (!guests.hasNext()) return overlappingDates;
        if (overlappingDates == null) throw new NullPointerException("List of overlapping dates is null");

        Guest g = guests.next();
        List<LocalDate> guestAvailability = new ArrayList<>(g.getAvailability().keySet());
        List<LocalDate> newOverlapping = overlappingDates
                .stream()
                .filter(guestAvailability::contains)
                .collect(toList());

        return getOverlappingDates(guests, newOverlapping);
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
