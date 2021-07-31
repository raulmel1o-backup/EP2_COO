package test.domain;

import main.domain.Guest;

import java.time.LocalDateTime;

class GuestTest {
    public static void main(String[] args) {
        Should_Create_New_Guest();
        Should_Add_Guest_Availability();
    }

    private static void Should_Create_New_Guest() {
        final String email = "abc@gmail.com";
        final Guest guest = new Guest(email);

        if (guest.getEmail().equals(email)) return;

        System.err.println("Guest instantiation went wrong");
    }

    private static void Should_Add_Guest_Availability() {
        final Guest guest = guestBuilder();

        guest.addAvailability(LocalDateTime.of(2021, 7, 22, 9, 40),
                 LocalDateTime.of(2021, 7, 22, 10, 45));

        guest.addAvailability(LocalDateTime.of(2021, 7, 22, 8, 15),
                LocalDateTime.of(2021, 7, 22, 10, 30));

        guest.addAvailability(LocalDateTime.of(2021, 7, 22, 22, 40),
                LocalDateTime.of(2021, 7, 22, 23, 55));

        guest.addAvailability(LocalDateTime.of(2021, 7, 22, 12, 40),
                LocalDateTime.of(2021, 7, 22, 14, 22));

        System.out.println("meua migo");
    }

    private static Guest guestBuilder() {
        final String email = "abc@email.com";
        return new Guest(email);
    }
}