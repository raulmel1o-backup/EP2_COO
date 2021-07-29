package infra.exception;

public class DifferentDatesException extends RuntimeException {
    public DifferentDatesException(final String message) {
        super(message);
    }
}
