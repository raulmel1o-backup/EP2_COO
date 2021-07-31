package test.domain;

import main.domain.TimeInterval;

import java.time.LocalDate;

class TimeIntervalTest {
    public static void main(String[] args) {
        Should_Build_TimeIntervals_List();
    }

    private static void Should_Build_TimeIntervals_List() {
        TimeInterval.buildTimeIntervalList(LocalDate.now());
    }
}