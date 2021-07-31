package main.domain;

import main.infra.exception.DifferentDatesException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Guest {

    private final String email;
    private final Map<LocalDate, List<TimeInterval>> availability;

    public Guest(String email) {
        this.email = email;
        this.availability = new HashMap<>();
    }

    public void addAvailability(LocalDateTime start, LocalDateTime end) {
        if (!start.toLocalDate().isEqual(end.toLocalDate()))
            throw new DifferentDatesException(start.toLocalDate() + " is not equal to " + end.toLocalDate());

        final LocalDate date = start.toLocalDate();

        if (!availability.containsKey(date)) availability.put(date, TimeInterval.buildTimeIntervalList(date));

        List<TimeInterval> timeIntervals = availability.get(date);

        for (TimeInterval timeInterval : timeIntervals) {
            if ((timeInterval.getStart().isEqual(start) || timeInterval.getStart().isAfter(start)) &&
                    (timeInterval.getStart().plusMinutes(15).isEqual(end) || timeInterval.getStart().plusMinutes(15).isBefore(end))) {
                timeInterval.setAvailable(true);
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public Map<LocalDate, List<TimeInterval>> getAvailability() {
        return availability;
    }
}
