package test.domain;

import main.domain.MeetingScheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MeetingSchedulerTest {
    public static void main(String[] args) {
        Should_Print_Overlapping_Times();
    }

    private static void Should_Print_Overlapping_Times() {
        final MeetingScheduler meetingScheduler = new MeetingScheduler();
        final LocalDate start = LocalDate.of(2021, 7, 22);
        final LocalDate end = LocalDate.of(2021, 7, 27);
        final List<String> guests = List.of("Xesque", "Dele", "Dale");

        meetingScheduler.scheduleMeetingBetween(start, end, guests);

        meetingScheduler.designateAvailability("Xesque",
                LocalDateTime.of(start, LocalTime.of(10, 45)),
                LocalDateTime.of(start, LocalTime.of(12, 15)));

        meetingScheduler.designateAvailability("Xesque",
                LocalDateTime.of(start.plusDays(1), LocalTime.of(9, 20)),
                LocalDateTime.of(start.plusDays(1), LocalTime.of(14, 30)));

        meetingScheduler.designateAvailability("Xesque",
                LocalDateTime.of(start, LocalTime.of(21, 45)),
                LocalDateTime.of(start, LocalTime.of(22, 10)));

        meetingScheduler.designateAvailability("Xesque",
                LocalDateTime.of(start, LocalTime.of(8, 10)),
                LocalDateTime.of(start, LocalTime.of(9, 50)));

        meetingScheduler.designateAvailability("Dele",
                LocalDateTime.of(start, LocalTime.of(10, 45)),
                LocalDateTime.of(start, LocalTime.of(12, 15)));

        meetingScheduler.designateAvailability("Dele",
                LocalDateTime.of(start.plusDays(2), LocalTime.of(9, 20)),
                LocalDateTime.of(start.plusDays(2), LocalTime.of(14, 30)));

        meetingScheduler.designateAvailability("Dele",
                LocalDateTime.of(start, LocalTime.of(21, 45)),
                LocalDateTime.of(start, LocalTime.of(22, 10)));

        meetingScheduler.designateAvailability("Dele",
                LocalDateTime.of(start, LocalTime.of(8, 10)),
                LocalDateTime.of(start, LocalTime.of(9, 50)));

        meetingScheduler.designateAvailability("Dale",
                LocalDateTime.of(start, LocalTime.of(10, 45)),
                LocalDateTime.of(start, LocalTime.of(12, 15)));

        meetingScheduler.designateAvailability("Dale",
                LocalDateTime.of(start.plusDays(3), LocalTime.of(9, 20)),
                LocalDateTime.of(start.plusDays(3), LocalTime.of(14, 30)));

        meetingScheduler.designateAvailability("Dale",
                LocalDateTime.of(start, LocalTime.of(21, 45)),
                LocalDateTime.of(start, LocalTime.of(22, 10)));

        meetingScheduler.designateAvailability("Dale",
                LocalDateTime.of(start, LocalTime.of(8, 10)),
                LocalDateTime.of(start, LocalTime.of(9, 50)));

        meetingScheduler.showOverlapping();
    }
}
