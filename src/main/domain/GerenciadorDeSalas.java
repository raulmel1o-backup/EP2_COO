package main.domain;

import main.infra.exception.DifferentDatesException;
import main.infra.exception.RoomAlreadyReservedException;
import main.infra.exception.RoomNotFoundException;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class GerenciadorDeSalas {
    private List<Sala> salas;
    private Map<String, Sala> salasPorNome;
    private List<Reserva> reservas;
    private Map<Sala, List<Reserva>> reservasPorSala;

    public GerenciadorDeSalas() {
        this.salas           = new ArrayList<>();
        this.reservas        = new ArrayList<>();
        this.salasPorNome    = new HashMap<>();
        this.reservasPorSala = new HashMap<>();
    }

    public void adicionaSala(String nome, int capacidadeMaxima, String descricao){
        Sala nova = new Sala(nome, descricao, capacidadeMaxima);
        salasPorNome.put(nome, nova);
        if(salas.stream()
                .filter(sala -> sala.getNome().equals(nome))
                .findAny()
                .isEmpty()
        ) salas.add(nova);
    }

    public Collection<Sala> getSalas(){
        return salas;
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime inicio, LocalDateTime fim)
            throws RoomNotFoundException, DifferentDatesException, RoomAlreadyReservedException
    {
        Reserva reserva = null;
        Sala sala = getSala(nomeDaSala).orElseThrow(new RoomNotFoundException(nomeDaSala));

        if(!inicio.toLocalDate().equals(fim.toLocalDate()))
            throw new DifferentDatesException(inicio.toLocalDate(), fim.toLocalDate());

        reserva = new Reserva(sala, inicio, fim);
        reservasPorSala.computeIfAbsent(sala, key -> new ArrayList<>());
        Collection<Reserva> reservasSala = reservasPraSala(sala);

        for (Reserva reserv : reservasSala) {
            if (reserv.doesItIntersects(inicio, fim)) throw new RoomAlreadyReservedException(sala, inicio, fim);
        }

        reservas.add(reserva);
        reservasPorSala.get(sala).add(reserva);
        return reserva;
    }

    public Collection<Reserva> reservasPraSala(String nomeSala){
        return reservasPorSala.get(getSala(nomeSala).orElse(null));
    }

    public Collection<Reserva> reservasPraSala(Sala sala){
        return reservasPorSala.get(sala);
    }

    public Optional<Sala> getSala(Sala salaProcurada){
        return salas.stream().filter(sala -> sala.equals(salaProcurada)).findFirst();
    }

    public Optional<Sala> getSala(String nome){
        return Optional.ofNullable(salasPorNome.get(nome));
    }

}