package main.domain;

import main.infra.exception.DifferentDatesException;
import main.infra.exception.RoomAlreadyReservedException;
import main.infra.exception.RoomNotFoundException;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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


    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime inicio, LocalDateTime fim)
            throws RoomNotFoundException, DifferentDatesException, RoomAlreadyReservedException
    {
        Reserva reserva;
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

    public void cancelaReserva(Reserva cancelada) throws RoomNotFoundException{
        if (cancelada == null) return;
        try{
            Sala sala = getSala(cancelada.sala()).orElseThrow(new RoomNotFoundException(cancelada.sala().getNome()));
            List<Reserva> reservasSala = new ArrayList<>(reservasPraSala(sala));
            reservasSala.remove(cancelada);
            reservasPorSala.put(cancelada.sala(), reservasSala);
            this.reservas.remove(cancelada);
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeSalaChamada(String nomeDaSala) throws RoomNotFoundException{
        Sala sala = getSala(nomeDaSala).orElseThrow(new RoomNotFoundException(nomeDaSala));
        List<Reserva> reservasDeletar = reservasPorSala.get(salasPorNome.get(nomeDaSala));
        if(reservasDeletar == null) return;
        for (Reserva reserva : reservasDeletar) {
            cancelaReserva(reserva);
        }
        salasPorNome.remove(nomeDaSala);
    }

    public void imprimeReservasPraSala(String nomeSala) throws RoomNotFoundException{
        Sala sala = getSala(nomeSala).orElseThrow(new RoomNotFoundException(nomeSala));
        if(reservasPraSala(sala) == null) return;
        reservasPraSala(nomeSala).forEach(System.out::println);

    }

    public Collection<Reserva> reservasPraSala(Sala sala){
        return reservasPorSala.get(sala);

    }

    public Collection<Reserva> reservasPraSala(String nomeSala){
        return reservasPorSala.get(getSala(nomeSala).orElse(null));
    }

    public List<Sala> listaDeSalas(){
        return new ArrayList<>(salasPorNome.values());
    }

    public Optional<Sala> getSala(Sala salaProcurada){
        return salas.stream().filter(sala -> sala.equals(salaProcurada)).findFirst();
    }

    public Optional<Sala> getSala(String nome){
        return Optional.ofNullable(salasPorNome.get(nome));
    }

    public Optional<Reserva> getReserva(Reserva reservationToFind){
        return reservas.stream().filter(reserva -> reserva.equals(reservationToFind)).findFirst();
    }

}