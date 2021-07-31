package main.domain;

import java.sql.Time;
import java.time.LocalDateTime;

public class Reserva {
    private final TimeInterval interval;
    private final Sala sala;

    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim){
        this.sala = sala;
        this.interval = new TimeInterval(inicio, fim);
    }

    public Sala sala() {return sala;}

    public LocalDateTime inicio() {return interval.getStart();}

    public LocalDateTime fim() {return interval.getEnd();}

}
