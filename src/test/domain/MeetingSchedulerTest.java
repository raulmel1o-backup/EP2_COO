package test.domain;

import main.domain.MarcadorDeReuniao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MeetingSchedulerTest {
    public static void main(String[] args) {
        Should_Print_Overlapping_Times();
    }

    private static void Should_Print_Overlapping_Times() {
        final MarcadorDeReuniao marcadorDeReuniao = new MarcadorDeReuniao();
        final LocalDate start = LocalDate.of(2021, 7, 22);
        final LocalDate end = LocalDate.of(2021, 7, 27);
        final List<String> guests = List.of("Xesque", "Dele", "Dale");

        marcadorDeReuniao.marcarReuniaoEntre(start, end, guests);

        marcadorDeReuniao.indicaDisponibilidade("Xesque",
                LocalDateTime.of(start, LocalTime.of(10, 45)),
                LocalDateTime.of(start, LocalTime.of(12, 15)));

        marcadorDeReuniao.indicaDisponibilidade("Xesque",
                LocalDateTime.of(start.plusDays(1), LocalTime.of(9, 20)),
                LocalDateTime.of(start.plusDays(1), LocalTime.of(14, 30)));

        marcadorDeReuniao.indicaDisponibilidade("Xesque",
                LocalDateTime.of(start, LocalTime.of(21, 45)),
                LocalDateTime.of(start, LocalTime.of(22, 10)));

        marcadorDeReuniao.indicaDisponibilidade("Xesque",
                LocalDateTime.of(start, LocalTime.of(8, 10)),
                LocalDateTime.of(start, LocalTime.of(9, 50)));

        marcadorDeReuniao.indicaDisponibilidade("Dele",
                LocalDateTime.of(start, LocalTime.of(10, 45)),
                LocalDateTime.of(start, LocalTime.of(12, 15)));

        marcadorDeReuniao.indicaDisponibilidade("Dele",
                LocalDateTime.of(start.plusDays(2), LocalTime.of(9, 20)),
                LocalDateTime.of(start.plusDays(2), LocalTime.of(14, 30)));

        marcadorDeReuniao.indicaDisponibilidade("Dele",
                LocalDateTime.of(start, LocalTime.of(21, 45)),
                LocalDateTime.of(start, LocalTime.of(22, 10)));

        marcadorDeReuniao.indicaDisponibilidade("Dele",
                LocalDateTime.of(start, LocalTime.of(8, 10)),
                LocalDateTime.of(start, LocalTime.of(9, 50)));

        marcadorDeReuniao.indicaDisponibilidade("Dale",
                LocalDateTime.of(start, LocalTime.of(10, 45)),
                LocalDateTime.of(start, LocalTime.of(12, 15)));

        marcadorDeReuniao.indicaDisponibilidade("Dale",
                LocalDateTime.of(start.plusDays(3), LocalTime.of(9, 20)),
                LocalDateTime.of(start.plusDays(3), LocalTime.of(14, 30)));

        marcadorDeReuniao.indicaDisponibilidade("Dale",
                LocalDateTime.of(start, LocalTime.of(21, 45)),
                LocalDateTime.of(start, LocalTime.of(22, 10)));

        marcadorDeReuniao.indicaDisponibilidade("Dale",
                LocalDateTime.of(start, LocalTime.of(8, 10)),
                LocalDateTime.of(start, LocalTime.of(9, 50)));

        marcadorDeReuniao.mostraSobreposicao();
    }
}
