package domain;

import infra.exception.DifferentDatesException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Guest {

    private final String email;
    private final Map<LocalDate, List<TimeInterval>> availability;

    public Guest(String email) {
        this.email = email;
        this.availability = new HashMap<>();
    }

    public void addAvailability(LocalDateTime start, LocalDateTime end) {
        if (!start.toLocalDate().isEqual(end.toLocalDate()))
            throw new DifferentDatesException(start.toLocalDate() + " is not equal to " + end.toLocalDate());

        final LocalDate date = start.toLocalDate();

        TimeInterval intervalToAdd = new TimeInterval(start, end);
        if (availability.containsKey(date)) {
            List<TimeInterval> timeIntervalList = availability.get(date);

            timeIntervalList.forEach(timeInterval -> {
                if (timeInterval.isWithin(start, end)) timeInterval.addInterval(start, end);
                else timeIntervalList.add(intervalToAdd);
            });

        } else {
            availability.put(date, List.of(intervalToAdd));
        }
    }

    public String getEmail() {
        return email;
    }

    public Map<LocalDate, List<TimeInterval>> getAvailability() {
        return availability;
    }
}
