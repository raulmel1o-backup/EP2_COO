package main.domain;

import main.infra.exception.DifferentDatesException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TimeInterval {

    private LocalDateTime start;
    private LocalDateTime end;

    public static boolean isInExclusiveRange(LocalDateTime min, LocalDateTime max, LocalDateTime time){
        return min.isBefore(time) && max.isAfter(time);
    }

    public boolean intersects(TimeInterval otherInterval){
        return isInExclusiveRange(this.start, this.end, otherInterval.getStart())
                || isInExclusiveRange(this.start, this.end, otherInterval.getEnd())
                || isInExclusiveRange(otherInterval.getStart(), otherInterval.getEnd(), this.start)
                || isInExclusiveRange(otherInterval.getStart(), otherInterval.getEnd(), this.end) ;
    }


    public static List<TimeInterval> divideIfIntersects(TimeInterval intervalA, TimeInterval intervalB) {
        if (!intervalA.intersects(intervalB)) return new ArrayList<>();

        List<TimeInterval> initialIntervals = List.of(intervalA, intervalB) ;

        List<LocalDateTime> sortedDateTimes = initialIntervals
                .stream()
                .map(interval -> List.of(interval.getStart(), interval.getEnd()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<TimeInterval> dividedIntervals = new ArrayList<>();
        int i = 1;
        for (int interval = 1 ; interval <= 2; interval++) {
            for (i = i - 1; i / 2 < interval ; i++) {
                LocalDateTime start = sortedDateTimes.get(i);
                LocalDateTime end   = sortedDateTimes.get(i + 1);
                if (!start.equals(end)) dividedIntervals.add(new TimeInterval(start, end));
            }
        }
        return dividedIntervals;
    }

    public void addInterval(LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(this.start)) this.start = start;
        if (end.isAfter(this.end)) this.end = end;
    }

    public boolean isWithin(LocalDateTime start, LocalDateTime end) {
        return !(start.isAfter(this.end) || end.isBefore(this.start));
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        if (!start.toLocalDate().isEqual(end.toLocalDate()))
            throw new DifferentDatesException(start.toLocalDate() + " is not equals to " + end.toLocalDate());

        this.start = start;
        this.end = end;
    }

}
