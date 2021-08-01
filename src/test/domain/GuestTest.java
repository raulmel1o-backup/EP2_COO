package test.domain;

import main.domain.Participante;

import java.time.LocalDateTime;

class GuestTest {
    public static void main(String[] args) {
        Should_Create_New_Guest();
        Should_Add_Guest_Availability();
    }

    private static void Should_Create_New_Guest() {
        final String email = "abc@gmail.com";
        final Participante participante = new Participante(email);

        if (participante.getEmail().equals(email)) return;

        System.err.println("Participante instantiation went wrong");
    }

    private static void Should_Add_Guest_Availability() {
        final Participante participante = guestBuilder();

        participante.addAvailability(LocalDateTime.of(2021, 7, 22, 9, 40),
                 LocalDateTime.of(2021, 7, 22, 10, 45));

        participante.addAvailability(LocalDateTime.of(2021, 7, 22, 8, 15),
                LocalDateTime.of(2021, 7, 22, 10, 30));

        participante.addAvailability(LocalDateTime.of(2021, 7, 22, 22, 40),
                LocalDateTime.of(2021, 7, 22, 23, 55));

        participante.addAvailability(LocalDateTime.of(2021, 7, 22, 12, 40),
                LocalDateTime.of(2021, 7, 22, 14, 22));

        System.out.println("meua migo");
    }

    private static Participante guestBuilder() {
        final String email = "abc@email.com";
        return new Participante(email);
    }
}