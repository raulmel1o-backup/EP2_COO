package test.domain;

import main.domain.TimeInterval;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class TimeIntervalTest {
    public static void main(String[] args) {
        Should_Create_Time_Interval();
        Should_Check_If_Time_Intersects_With_Existing_Interval();
    }

    private static void Should_Create_Time_Interval() {
        final LocalDate date = LocalDate.of(2021, 7, 22);
        final LocalDateTime start = LocalDateTime.of(date, LocalTime.of(12, 40));
        final LocalDateTime end = LocalDateTime.of(date, LocalTime.of(12, 54));

        final TimeInterval timeInterval = new TimeInterval(start, end);

        if (!timeInterval.getStart().equals(start)) {
            System.err.println("TimeInterval start time should be " + start +
                    " but it was " + timeInterval.getStart());

            return;
        }

        if (!timeInterval.getEnd().equals(end)) {
            System.err.println("TimeInterval start time should be " + end +
                    " but it was " + timeInterval.getEnd());
        }
    }

    private static void Should_Check_If_Time_Intersects_With_Existing_Interval() {
        final LocalDate date = LocalDate.of(2021, 7, 22);
        final LocalDateTime start = LocalDateTime.of(date, LocalTime.of(12, 40));
        final LocalDateTime end = LocalDateTime.of(date, LocalTime.of(12, 54));

        final TimeInterval timeInterval = new TimeInterval(start, end);

        if (!timeInterval.isWithin(start.minusMinutes(2), end)) {
            System.err.println("Should intersect");
            return;
        }

        if (!timeInterval.isWithin(start.plusMinutes(3), end)) {
            System.err.println("Should intersect");
            return;
        }

        if (!timeInterval.isWithin(start, end.minusMinutes(5))) {
            System.err.println("Should intersect");
            return;
        }

        if (!timeInterval.isWithin(start, end.plusMinutes(60))) {
            System.err.println("Should intersect");
            return;
        }

        if (!timeInterval.isWithin(start.minusMinutes(10), end.plusMinutes(20))) {
            System.err.println("Should intersect");
            return;
        }

        if (!timeInterval.isWithin(start.plusMinutes(3), end.minusMinutes(2))) {
            System.err.println("Should intersect");
        }
    }
}