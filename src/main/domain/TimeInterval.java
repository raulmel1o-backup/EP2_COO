package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class TimeInterval {

    private LocalDateTime start;
    private LocalDateTime end;
    private boolean available;

    public static List<TimeInterval> buildTimeIntervalList(LocalDate date) {
        final ArrayList<TimeInterval> timeIntervals = new ArrayList<>();

        for (int i = 0; i < 96; i++) {
            LocalDateTime time = LocalDateTime.of(date, LocalTime.MIDNIGHT).plusMinutes(i * 15);
            TimeInterval interval = new TimeInterval(time, time.plusMinutes(15));

            timeIntervals.add(interval);
        }

        return timeIntervals;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.available = false;
    }

}
