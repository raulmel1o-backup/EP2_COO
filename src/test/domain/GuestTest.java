package test.domain;

import main.domain.Guest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class GuestTest {
    public static void main(String[] args) {
        Should_Create_New_Guest();
        Should_Add_Availability_To_Guest();
    }

    private static void Should_Create_New_Guest() {
        final String email = "abc@gmail.com";
        final Guest guest = new Guest(email);

        if (guest.getEmail().equals(email)) return;

        System.err.println("Guest instantiation went wrong");
    }

    private static void Should_Add_Availability_To_Guest() {
        final Guest guest = guestBuilder();
        final LocalDate date = LocalDate.of(2021, 7, 22);
        final LocalDateTime start = LocalDateTime.of(date, LocalTime.of(12, 40));
        final LocalDateTime end = LocalDateTime.of(date, LocalTime.of(12, 54));



        if (!guest.getAvailability().isEmpty()) {
            System.err.println("Guest availability map should be empty but it has "
                    + guest.getAvailability().size() + " elements");

            return;
        }

        guest.addAvailability(start, end);

        if (guest.getAvailability().size() != 1) {
            System.err.println("Guest availability map should contain one element but it has "
                    + guest.getAvailability().size() + " elements");

            return;
        }

        if (!guest.getAvailability().containsKey(date)) {
            System.err.println("Guest availability map should contain " + date);

            return;
        }

        if (!guest.getAvailability().get(date).get(0).getStart().equals(start)) {
            System.err.println("TimeInterval start time should be " + start + " but it was "
                    + guest.getAvailability().get(date).get(0).getStart());

            return;
        }

        if (!guest.getAvailability().get(date).get(0).getEnd().equals(end)) {
            System.err.println("TimeInterval end time should be " + end + " but it was "
                    + guest.getAvailability().get(date).get(0).getEnd());
        }
    }

    private static Guest guestBuilder() {
        final String email = "abc@email.com";
        return new Guest(email);
    }
}