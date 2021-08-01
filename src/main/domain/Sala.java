package main.domain;

import main.infra.exception.NegativeOrZeroCapacityException;

public class Sala {
    private final String nome;
    private final String local;
    private final String observacoes;
    private final int capacidadeMax;

    public Sala(String nome, String observacoes, int capacidade){
        this.nome           = nome;
        this.capacidadeMax  = capacidade;
        this.observacoes    = observacoes;
        this.local          = "";

        throwExceptionIfNegativeOrZeroCapacity();
    }

    public Sala(String nome, String observacoes, int capacidade, String local){
        this.nome           = nome;
        this.capacidadeMax  = capacidade;
        this.observacoes    = observacoes;
        this.local          = local;

        throwExceptionIfNegativeOrZeroCapacity();
    }

    private void throwExceptionIfNegativeOrZeroCapacity() {
        if (capacidadeMax < 0) throw new NegativeOrZeroCapacityException(capacidadeMax);
    }

    @Override
    public String toString(){
        return "Nome: \"" + nome + "\" Capacidade Maxima: " + capacidadeMax + "\n"
                + "Observacoes: \"" + observacoes  + "\" Local: \"" + local + "\"\n";
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
