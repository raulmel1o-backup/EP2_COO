package main.domain;

import infra.exception.GuestNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class MarcadorDeReuniao {

    private LocalDate start;
    private LocalDate end;
    private final List<Participante> guestsList;

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) {
        this.start = dataInicial;
        this.end = dataFinal;

        listaDeParticipantes.forEach(email -> this.guestsList.add(new Participante(email)));
    }

    public void indicaDisponibilidade(String participante, LocalDateTime inicio, LocalDateTime fim) {
        if (inicio.toLocalDate().isBefore(this.start) || fim.toLocalDate().isAfter(this.end)) {
            System.err.println("Invalid dates");
        }

        Participante convidado = guestsList.stream().filter(g -> g.getEmail().equals(participante)).collect(toList()).get(0);
        if (convidado == null) {
            throw new GuestNotFoundException();
        }

        convidado.addAvailability(inicio, fim);
    }
  
    public void mostraSobreposicao() {
        final Map<LocalDate, Map<LocalDateTime, List<Participante>>> map = new LinkedHashMap<>();
        LocalDate day = start;

        while (!day.isAfter(end)) {
            final HashMap<LocalDateTime, List<Participante>> mapToAdd = new LinkedHashMap<>();

            for (int i = 0; i < 96; i++) {
                mapToAdd.put(LocalDateTime.of(day, LocalTime.MIDNIGHT.plusMinutes(i * 15)), new ArrayList<>());
            }

            map.put(day, mapToAdd);
            day = day.plusDays(1);
        }

        for (Participante participante : guestsList) {
            final Set<LocalDate> keys = participante.getAvailability().keySet();

            for (LocalDate key : keys) {
                participante.getAvailability().get(key).forEach(timeInterval -> {
                    if (timeInterval.isDisponivel()) {
                        map.get(timeInterval.getInicio().toLocalDate()).get(timeInterval.getInicio()).add(participante);
                    }
                });
            }
        }

        Printer.print(start, end, guestsList, map);
    }

    private List<LocalDate> getSortedOverlappingDates() {
        ListIterator<Participante> guests = guestsList.listIterator();

        return getOverlappingDates(guests, new ArrayList<>())
                .stream()
                .sorted()
                .collect(toList());
    }

    private List<LocalDate> getOverlappingDates(ListIterator<Participante> guests, List<LocalDate> overlappingDates) {
        if (!guests.hasNext()) return overlappingDates;
        if (overlappingDates == null) throw new NullPointerException("List of overlapping dates is null");

        Participante g = guests.next();
        List<LocalDate> guestAvailability = new ArrayList<>(g.getAvailability().keySet());
        List<LocalDate> newOverlapping = overlappingDates
                .stream()
                .filter(guestAvailability::contains)
                .collect(toList());

        return getOverlappingDates(guests, newOverlapping);
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public List<Participante> getGuestsList() {
        return guestsList;
    }

    public MarcadorDeReuniao() {
        this.guestsList = new ArrayList<>();
    }
}
