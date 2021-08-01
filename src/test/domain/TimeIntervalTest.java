package test.domain;

import main.domain.IntervaloTempo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class TimeIntervalTest {
    public static void main(String[] args) {
        Should_Build_TimeIntervals_List();
        Should_Obtain_Exact_Number_of_Days();
    }

    private static void Should_Build_TimeIntervals_List() {
        IntervaloTempo.buildTimeIntervalList(LocalDate.now());
    }

    private static void Should_Obtain_Exact_Number_of_Days(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plusDays(1);
        IntervaloTempo test = new IntervaloTempo(next, now);
        List<LocalDate> days = IntervaloTempo.getDatesBetween(now, next);
        if(days.size() < 2){
            System.out.println("getDatesBetween() method is not accounting all possible days");
        }
        else if(days.size() > 2){
            System.out.println("getDatesBetween() method is accounting more days than it should");
        }
        if(!days.containsAll(IntervaloTempo.getDatesBetween(next, now))){
            System.out.println("getDatesBetween() method is not reflexive");
        }
        if(!List.of(now.toLocalDate(), now.plusDays(1).toLocalDate()).containsAll(days)) {
            System.out.println("getDatesBetween() method added more days than it should");
        }
    }
}