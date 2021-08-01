package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Printer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void print(LocalDate start, LocalDate end, List<Convidado> convidados, Map<LocalDate, Map<LocalDateTime, List<Convidado>>> availability) {
        System.out.println("\t\tQUADRO DE DISPONIBILIDADES\n");
        printHeaders(start, end);
        printDisponibilidade(start, convidados, availability);
    }

    private static void printHeaders(LocalDate start, LocalDate end) {
        System.out.println(buildHeaders(start, end));
    }

    private static void printDisponibilidade(LocalDate start, List<Convidado> convidados, Map<LocalDate, Map<LocalDateTime, List<Convidado>>> availability) {
        if (availability.isEmpty()) {
            System.out.println("Nenhum horário registrado");
            return;
        }

        final List<LocalDateTime> optimalDateTimes = new ArrayList<>();

        final Set<LocalDate> dateKeys = availability.keySet();
        final Set<LocalDateTime> dateTimeKeys = availability.get(start).keySet();

        for (LocalDateTime dateTimeKey : dateTimeKeys) {
            System.out.print(dateTimeKey.toLocalTime() + "\t|");
            for (LocalDate dateKey : dateKeys) {
                if (availability.get(dateKey).get(LocalDateTime.of(dateKey, dateTimeKey.toLocalTime())).size() == 0) {
                    System.out.print("     " + ANSI_RED + availability.get(dateKey).get(LocalDateTime.of(dateKey, dateTimeKey.toLocalTime())).size() + ANSI_RESET + "      |");
                } else if (availability.get(dateKey).get(LocalDateTime.of(dateKey, dateTimeKey.toLocalTime())).size() < convidados.size()) {
                    System.out.print("     " + ANSI_YELLOW + availability.get(dateKey).get(LocalDateTime.of(dateKey, dateTimeKey.toLocalTime())).size() + ANSI_RESET + "      |");
                } else if (availability.get(dateKey).get(LocalDateTime.of(dateKey, dateTimeKey.toLocalTime())).size() == convidados.size()) {
                    optimalDateTimes.add(LocalDateTime.of(dateKey, dateTimeKey.toLocalTime()));
                    System.out.print("     " + ANSI_GREEN + availability.get(dateKey).get(LocalDateTime.of(dateKey, dateTimeKey.toLocalTime())).size() + ANSI_RESET + "      |");
                }
            }

            System.out.println();
        }

        System.out.println("\nHORÁRIOS IDEAIS\n");
        printHorariosIdeais(optimalDateTimes);
    }

    private static void printHorariosIdeais(List<LocalDateTime> localDateTimes) {
        for (LocalDateTime localDateTime : localDateTimes) {
            System.out.println(localDateTime.toLocalTime() + " - " + localDateTime.toLocalDate());
        }
    }

    private static String buildHeaders(LocalDate start, LocalDate end) {
        final StringBuilder headers = new StringBuilder("\t\t|");
        LocalDate day = start;

        while (!day.isAfter(end)) {
            headers.append(" ").append(day).append(" |");
            day = day.plusDays(1);
        }

        return headers.toString();
    }
}
