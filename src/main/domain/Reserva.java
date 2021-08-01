package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private final LocalDateTime inicio, fim;
    private final List<TimeInterval> intervals;
    private final Sala sala;

    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim){
        this.sala = sala;
        this.intervals = TimeInterval.listAllAvailableIntervals(inicio, fim);
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean doesItIntersects(LocalDateTime start, LocalDateTime end){
        TimeInterval thisInterval = new TimeInterval(this.inicio, this.fim);
        return thisInterval.intersects(new TimeInterval(start, end));
    }

    public Sala sala() {return sala;}

    public LocalDateTime inicio() {return inicio;}

    public LocalDateTime fim() {return fim;}
}
