package main.domain;

import java.util.*;

public class GerenciadorDeSalas {
    private List<Sala> salas;
    private Map<String, Sala> salasPorNome;
    private List<Reserva> reservas;
    private Map<Sala, Reserva> reservasPorSala;

    public GerenciadorDeSalas() {
        this.salas = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.salasPorNome = new HashMap<>();
        this.reservasPorSala = new HashMap<>();
    }

    public void adicionaSala(String nome, int capacidadeMaxima, String descricao){
        Sala nova = new Sala(nome, descricao, capacidadeMaxima);
        salasPorNome.put(nome, nova);
        if(!salas.contains(nova)) salas.add(nova);
    }

    public Optional<Sala> getSala(Sala salaProcurada){
        return salas.stream().filter(sala -> sala.equals(salaProcurada)).findFirst();
    }
    public Optional<Sala> getSala(String nome){
        return Optional.of(salasPorNome.get(nome));
    }

}
