package main.domain;

import main.infra.exception.DifferentDatesException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Convidado {

    private final String email;
    private final Map<LocalDate, List<IntervaloTempo>> availability;

    public Convidado(String email) {
        this.email = email;
        this.availability = new HashMap<>();
    }

    public void addAvailability(LocalDateTime start, LocalDateTime end) {
        if (!start.toLocalDate().isEqual(end.toLocalDate()))
            throw new DifferentDatesException(start.toLocalDate(), end.toLocalDate());

        final LocalDate date = start.toLocalDate();

        if (!availability.containsKey(date)) availability.put(date, IntervaloTempo.buildTimeIntervalList(date));

        final List<IntervaloTempo> intervaloTempos = availability.get(date);
        IntervaloTempo.adicionaDisponibilidade(start, end, intervaloTempos);
    }

    public String getEmail() {
        return email;
    }

    public Map<LocalDate, List<IntervaloTempo>> getAvailability() {
        return availability;
    }
}
