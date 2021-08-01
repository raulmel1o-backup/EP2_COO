package main.infra.exception;

import main.domain.Sala;

import java.time.LocalDateTime;

public class RoomAlreadyReservedException extends Exception {
    public RoomAlreadyReservedException(Sala room, LocalDateTime inicio, LocalDateTime fim) {
       super("Somewhere at " + inicio + " to " + fim + " room is full.\nRoom: \n" + room.toString());
    }
}
