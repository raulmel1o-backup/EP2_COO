package test.domain;

import main.domain.GerenciadorDeSalas;
import main.domain.Sala;

import java.util.Optional;

public class RoomManagerTest {
    public static void main(String[] args) {

    }

    public static void Should_Add_Room(){
        GerenciadorDeSalas g = roomManagerBuilder();
        String nome = "redrum";
        String desc = "murder run";
        int cap = 3;

        g.adicionaSala(nome, cap, desc);

        Optional<Sala> getByObject, getByName = g.getSala(nome);
        Sala sala;

        if(getByName.isPresent()){
            sala = getByName.get();
            if(!sala.getNome().equals("redrum")) System.out.println("Wrong room name");
        }
        else {
            System.out.println("getSala(String nome) method is not finding room just added");
            return;
        }

        getByObject = g.getSala(sala);
        if(getByObject.isPresent()){
            sala = getByObject.get();
            if(!sala.getNome().equals("redrum")) System.out.println("Wrong room name");
        }
        else {
            System.out.println("getSala(Sala sala) method is not finding room just added");
            return;
        }
    }

    private static GerenciadorDeSalas roomManagerBuilder(){
        return new GerenciadorDeSalas();
    }
}
