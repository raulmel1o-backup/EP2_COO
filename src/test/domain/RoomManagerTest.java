package test.domain;

import main.domain.GerenciadorDeSalas;
import main.domain.Reserva;
import main.domain.Sala;
import main.infra.exception.DifferentDatesException;
import main.infra.exception.RoomAlreadyReservedException;
import main.infra.exception.RoomNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

public class RoomManagerTest {
    public static void main(String[] args) {
        Should_Add_Room();
        Should_Raise_RoomAlreadyReservedException();
        Should_Raise_RoomNotFoundException();
        Should_Raise_DifferentDaysException();
        Should_Print_Reservations();
        Should_Remove_Reservation();
    }

    private static void Should_Add_Room(){
        GerenciadorDeSalas g = roomManagerBuilder();
        Optional<Sala> getByObject, getByName = g.getSala("redrum");
        Sala sala;
        System.out.println("==============Should_Add_Room==================");

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
            Sala mesmaSala = getByObject.get();
            if(!sala.equals(mesmaSala))
                System.out.println("getSala(getSala(nome)) return a different room than getSala(nome)");
        }
        else {
            System.out.println("getSala(Sala sala) method is not finding room just added");
        }
    }

    private static void Should_Raise_RoomAlreadyReservedException(){
        GerenciadorDeSalas manager = roomManagerBuilder();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("==============Should_Raise_RoomAlreasyReservedException==================");
        try {
            System.out.println("First try");
            manager.reservaSalaChamada("redrum", now, now.plusMinutes(15));
            System.out.println("Second Try");
            manager.reservaSalaChamada("redrum", now.plusMinutes(15), now.plusMinutes(20));
            System.out.println("Third Try");
            manager.reservaSalaChamada("redrum", now, now.plusMinutes(20));
        }
        catch (RoomNotFoundException | DifferentDatesException ignored){
        }
        catch (RoomAlreadyReservedException reservedException){
            System.out.println(reservedException.getMessage());
        }
    }

    private static void Should_Raise_RoomNotFoundException(){
        GerenciadorDeSalas manager = roomManagerBuilder();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("==============Should_Raise_RoomNotFoundException==================");
        try {
            System.out.println("First try");
            manager.reservaSalaChamada("redrum", now, now.plusMinutes(15));
            System.out.println("Second Try");
            manager.reservaSalaChamada("murder", now, now.plusMinutes(15));
            System.out.println("Third Try");
            manager.reservaSalaChamada("redrum", now, now.plusMinutes(20));
        }
        catch (RoomNotFoundException notFoundException){
            System.out.println(notFoundException.getMessage());
        }
        catch (RoomAlreadyReservedException | DifferentDatesException ignored){
        }
    }

    private static void Should_Raise_DifferentDaysException(){
        GerenciadorDeSalas manager = roomManagerBuilder();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("==============Should_Raise_DifferentDaysException==================");
        try {
            System.out.println("First try");
            manager.reservaSalaChamada("redrum", now, now.plusMinutes(15));
            System.out.println("Second Try");
            manager.reservaSalaChamada("redrum", now.plusMinutes(15), now.plusMinutes(20));
            System.out.println("Third Try");
            manager.reservaSalaChamada("redrum", now.plusDays(1), now.plusMinutes(20));
        }
        catch (RoomNotFoundException | RoomAlreadyReservedException ignored){
        } catch (DifferentDatesException diffDatesException){
            System.out.println(diffDatesException.getMessage());
        }
    }

    private static void Should_Print_Reservations(){
       System.out.println("==============Should_Print_Reservations==================");
       GerenciadorDeSalas g = roomManagerBuilder();
       try {
           g.reservaSalaChamada("redrum", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
           g.imprimeReservasPraSala("redrum");
       }
       catch (RoomNotFoundException exc){
           System.out.println("wasn't able to find room");
       }
       catch (RoomAlreadyReservedException exc) {
           System.out.println("Room should not be reserved since it was added only once");
       }
    }

    private static void Should_Remove_Reservation(){
        System.out.println("==============Should_Remove_Reservation==================");
        GerenciadorDeSalas g = roomManagerBuilder();
        try {
            Reserva r = g.reservaSalaChamada("redrum",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15));
            g.reservaSalaChamada("redrum",
                    LocalDateTime.now().plusMinutes(15),
                    LocalDateTime.now().plusMinutes(30));
            g.cancelaReserva(r);
            if(g.getReserva(r).isPresent() || g.reservasPraSala("redrum").contains(r)) System.out.println("Reservation was not removed.");
        }
        catch (RoomNotFoundException exc){
            System.out.println("Wasn't able to find room");
        }
        catch (RoomAlreadyReservedException exc) {
            System.out.println("Room should not be reserved since it was added only once");
        }
    }
    private static GerenciadorDeSalas roomManagerBuilder(){
        GerenciadorDeSalas g = new GerenciadorDeSalas();
        String nome = "redrum", desc = "Here's Johnny!";
        int cap = 3;

        g.adicionaSala(nome, cap, desc);

        return g;
    }
}
