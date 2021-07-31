package main.domain;

import main.infra.exception.NegativeOrZeroCapacityException;

public class Sala {
    private String nome = "", local = "", observacoes = "";
    private int capacidadeMax = 0;

    public Sala(String nome, String observacoes, int capacidade){
        this.nome = nome;
        this.capacidadeMax = capacidade;
        this.observacoes = observacoes;
        throwExceptionIfNegativeOrZeroCapacity();
    }

    public Sala(String nome, String observacoes, int capacidade, String local){
        this.nome = nome;
        this.capacidadeMax = capacidade;
        this.observacoes = observacoes;
        this.local = local;
        throwExceptionIfNegativeOrZeroCapacity();
    }

    private void throwExceptionIfNegativeOrZeroCapacity() {
        if(capacidadeMax < 0) throw new NegativeOrZeroCapacityException(capacidadeMax);
    }

    public int getCapacidade() {
        return capacidadeMax;
    }

    public String getLocal() {
        return local;
    }

    public String getNome() {
        return nome;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
