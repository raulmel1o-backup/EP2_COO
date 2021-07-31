package main.domain;

import main.infra.exception.DifferentDatesException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Guest {

    private final String email;
    private final Map<LocalDate, ArrayList<TimeInterval>> availability;
    private final Map<LocalDate, List<TimeInterval>> newAvailability;

    public Guest(String email) {
        this.email = email;
        this.availability = new HashMap<>();
        this.newAvailability = new HashMap<>();
    }

    public void addAvailability(LocalDateTime start, LocalDateTime end) {
        if (!start.toLocalDate().isEqual(end.toLocalDate()))
            throw new DifferentDatesException(start.toLocalDate() + " is not equal to " + end.toLocalDate());

        final LocalDate date = start.toLocalDate();

        if (newAvailability.containsKey(date)) {
            List<TimeInterval> timeIntervals = newAvailability.get(date);

            for (TimeInterval timeInterval : timeIntervals) {
                if ((timeInterval.getStart().isEqual(start) || timeInterval.getStart().isAfter(start)) &&
                        (timeInterval.getStart().plusMinutes(15).isEqual(end) || timeInterval.getStart().plusMinutes(15).isBefore(end))) {
                    timeInterval.setAvailable(true);
                }
            }

        } else {
            newAvailability.put(date, TimeInterval.buildTimeIntervalList(date));
        }
    }

    public String getEmail() {
        return email;
    }


    public Map<LocalDate, ArrayList<TimeInterval>> getAvailability() {
        return availability;
    }
}
