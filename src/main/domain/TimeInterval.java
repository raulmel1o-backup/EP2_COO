package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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

    public static List<TimeInterval> listAllAvailableIntervals(LocalDateTime start, LocalDateTime end){
        List<TimeInterval> intervalsOnDay = TimeInterval.buildTimeIntervalList(start.toLocalDate());
        TimeInterval.addAvailability(start, end, intervalsOnDay);
        return intervalsOnDay
                .stream()
                .filter(TimeInterval::isAvailable)
                .collect(Collectors.toList());
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

    public static boolean isInRange(LocalDateTime minExclusive, LocalDateTime maxExclusive, LocalDateTime time) {
        return minExclusive.isBefore(time) && maxExclusive.isAfter(time);
    }

    public boolean intersects(TimeInterval otherInterval){
        return isInRange(this.start, this.end, otherInterval.getStart())
                || isInRange(this.start, this.end, otherInterval.getEnd())
                || isInRange(otherInterval.getStart(), otherInterval.getEnd(), this.start)
                || isInRange(otherInterval.getStart(), otherInterval.getEnd(), this.end)
                || hasSameStartAndEnd(otherInterval);
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

    public boolean hasSameStartAndEnd(TimeInterval a){
       return a.getStart().equals(this.start)
               && a.getEnd().equals(this.end);
    }

    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.available = false;
    }

}
