package main.infra.exception;

import java.util.function.Supplier;

public class RoomNotFoundException extends Exception implements Supplier<RoomNotFoundException> {
    public RoomNotFoundException(final String name){
    super("Room with name \"" + name + "\" was not found");
    }

    @Override
    public RoomNotFoundException get() {
        return this;
    }

}
