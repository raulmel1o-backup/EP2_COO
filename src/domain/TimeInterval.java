package domain;

import infra.exception.DifferentDatesException;

import java.time.LocalDateTime;

public class TimeInterval {

    private LocalDateTime start;
    private LocalDateTime end;

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
