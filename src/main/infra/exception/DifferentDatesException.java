package main.infra.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DifferentDatesException extends RuntimeException {
    public DifferentDatesException(final LocalDate dateA, final LocalDate dateB) {
        super(dateA + " is not equal to " + dateB);
    }
}
