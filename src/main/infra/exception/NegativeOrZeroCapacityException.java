package main.infra.exception;

public class NegativeOrZeroCapacityException extends IndexOutOfBoundsException{
    public NegativeOrZeroCapacityException(int cap){
        super("Capacity is negative or zero: " + cap);
    }
}
