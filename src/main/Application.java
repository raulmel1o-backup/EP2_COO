package main;

import main.domain.GerenciadorDeSalas;
import main.domain.MeetingScheduler;
import main.infra.exception.RoomAlreadyReservedException;
import main.infra.exception.RoomNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Application {

    private static final MeetingScheduler meetingScheduler = new MeetingScheduler();
    private static final GerenciadorDeSalas gerenciadorDeSalas = new GerenciadorDeSalas();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("fim")) {

            switch (input) {
                case "marcar":
                    handleScheduling(scanner);
                    break;
                case "indicar":
                    handleDesignatingAvailability(scanner);
                    break;
                case "sobreposicao":
                    handlePrintingOverlap();
                    break;
                case "adiciona":
                    handleAddingRoom(scanner);
                    break;
                case "remove":
                    handleRemovingRoom();
                    break;
                case "reserva":
                    handleBookingRoom(scanner);
                    break;
                case "cancela":
                    handleCancelingRoomBooking(scanner);
                    break;
                case "reservas":
                    handlePrintingBookings(scanner);
                    break;
            }


            input = scanner.nextLine();
        }
    }

    private static void handleScheduling(final Scanner scanner) {
        System.out.println("Entre quais dias você gostaria de marcar a reunião?");

        System.out.println("Insira o dia inicial (formato YYYY-MM-DD");
        final String startStr = scanner.nextLine();

        System.out.println("Insira o dia final (formato YYYY-MM-DD");
        final String endStr = scanner.nextLine();

        System.out.println("Insira o email dos convidados separado por ';' (ex: convidado1@email.com;convidado2@email.com");
        final String guestsStr = scanner.nextLine();

        try {
            meetingScheduler.scheduleMeetingBetween(LocalDate.parse(startStr),
                    LocalDate.parse(endStr), Arrays.asList(guestsStr.split(";")));
        } catch (DateTimeParseException exception) {
            System.err.println("Insira as datas no formato indicado");
        }
    }

    private static void handleDesignatingAvailability(final Scanner scanner) {
        System.out.println("Indique em quais horários você está disponível");

        System.out.println("Insira o seu email");
        final String emailStr = scanner.nextLine();

        System.out.println("Insira o horario inicial (formato YYYY-MM-DDTHH-mm)");
        final String startStr = scanner.nextLine();

        System.out.println("Insira o horario final (formato YYYY-MM-DDTHH-mm)");
        final String endStr = scanner.nextLine();

        try {
            meetingScheduler.designateAvailability(emailStr, LocalDateTime.parse(startStr), LocalDateTime.parse(endStr));
        } catch (DateTimeParseException exception) {
            System.err.println("Insira as datas no formato indicado");
        }
    }

    private static void handlePrintingOverlap() {
        meetingScheduler.showOverlapping();
    }

    private static void handleAddingRoom(final Scanner scanner) {
        System.out.println("Insira o nome da sala");
        final String name = scanner.nextLine();

        System.out.println("Insira a capacidade máxima da sala");
        final String maxCapacity = scanner.nextLine();

        System.out.println("Insira uma descrição da sala");
        final String description = scanner.nextLine();

        try {
            gerenciadorDeSalas.adicionaSala(name, Integer.parseInt(maxCapacity), description);
        } catch (NumberFormatException exception) {
            System.err.println("Insira um número inteiro válido");
        }
    }

    private static void handleRemovingRoom() {
    }

    private static void handleBookingRoom(final Scanner scanner) {
        System.out.println("Insira o nome da sala que será reservada");
        final String name = scanner.nextLine();

        System.out.println("Insira o horario inicial (formato YYYY-MM-DDTHH-mm)");
        final String start = scanner.nextLine();

        System.out.println("Insira o horario final (formato YYYY-MM-DDTHH-mm)");
        final String end = scanner.nextLine();

        try {
            gerenciadorDeSalas.reservaSalaChamada(name, LocalDateTime.parse(start), LocalDateTime.parse(end));
        } catch (RoomAlreadyReservedException exception) {
            System.err.println("Esta sala já está reservada neste horário");
        } catch (RoomNotFoundException exception) {
            System.err.println("Sala não encontrada");
        } catch (DateTimeParseException exception) {
            System.err.println("Insira as datas no formato indicado");
        }
    }

    private static void handleCancelingRoomBooking(final Scanner scanner) {
    }

    private static void handlePrintingBookings(final Scanner scanner) {
        System.out.println("Insira o nome da sala");
        final String name = scanner.nextLine();
    }
}
