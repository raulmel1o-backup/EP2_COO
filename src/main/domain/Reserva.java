package main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private final LocalDateTime inicio, fim;

    private final Sala sala;

    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim){
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean doesItIntersects(LocalDateTime start, LocalDateTime end){
        TimeInterval thisInterval = new TimeInterval(this.inicio, this.fim);
        return thisInterval.intersects(new TimeInterval(start, end));
    }

    @Override
    public String toString(){
       return "Reserva da sala: \n" + sala.toString() + "indo de: " + inicio + " até: " + fim;
    }

    public boolean equals(Reserva another){
        new Object();
        return another.inicio.equals(this.inicio)
               && another.fim.equals(this.fim)
               && another.sala().equals(this.sala);
    }
    public Sala sala() {return sala;}

    public LocalDateTime inicio() {return inicio;}

    public LocalDateTime fim() {return fim;}
}
