package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TimeInterval {

    private LocalDateTime start;
    private LocalDateTime end;
    private boolean available;


    public static void addAvailability(LocalDateTime start, LocalDateTime end, List<TimeInterval> timeIntervals) {
        for (TimeInterval timeInterval : timeIntervals) {
            if ((timeInterval.getStart().isEqual(start) || timeInterval.getStart().isAfter(start)) &&
                    (timeInterval.getStart().plusMinutes(15).isEqual(end) || timeInterval.getStart().plusMinutes(15).isBefore(end))) {
                timeInterval.setAvailable(true);
            }
        }
    }

    public static List<TimeInterval> buildTimeIntervalList(LocalDate date) {
        final ArrayList<TimeInterval> timeIntervals = new ArrayList<>();

        for (int i = 0; i < 96; i++) {
            LocalDateTime time = LocalDateTime.of(date, LocalTime.MIDNIGHT).plusMinutes(i * 15);
            TimeInterval interval = new TimeInterval(time, time.plusMinutes(15));

            timeIntervals.add(interval);
        }

        return timeIntervals;
    }

    public static List<LocalDate> getDatesBetween(LocalDateTime localDateTime1Inclusive,
                                           LocalDateTime localDateTime2Inclusive)
    {
        if(localDateTime1Inclusive.isBefore(localDateTime2Inclusive))
            getDatesBetween(localDateTime2Inclusive, localDateTime1Inclusive);
        List<LocalDate> dates = new ArrayList<>();
        LocalDate start = localDateTime1Inclusive.toLocalDate();
        int days = (int) ChronoUnit.DAYS.between(localDateTime1Inclusive, localDateTime2Inclusive.plusDays(1));
        for(int numDays = 0; numDays < days; numDays++) dates.add(start.plusDays(numDays));
        return dates;
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
