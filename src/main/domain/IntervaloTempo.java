package main.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class IntervaloTempo {

    private LocalDateTime inicio;
    private LocalDateTime fim;
    private boolean disponivel;

    public static void adicionaDisponibilidade(LocalDateTime inicio, LocalDateTime fim, List<IntervaloTempo> intervaloTempos) {
        for (IntervaloTempo intervaloTempo : intervaloTempos) {
            if ((intervaloTempo.getInicio().isEqual(inicio) || intervaloTempo.getInicio().isAfter(inicio)) &&
                    (intervaloTempo.getInicio().plusMinutes(15).isEqual(fim) || intervaloTempo.getInicio().plusMinutes(15).isBefore(fim))) {
                intervaloTempo.setDisponivel(true);
            }
        }
    }

    public static List<IntervaloTempo> buildTimeIntervalList(LocalDate data) {
        final ArrayList<IntervaloTempo> intervaloTempos = new ArrayList<>();

        for (int i = 0; i < 96; i++) {
            LocalDateTime time = LocalDateTime.of(data, LocalTime.MIDNIGHT).plusMinutes(i * 15);
            IntervaloTempo interval = new IntervaloTempo(time, time.plusMinutes(15));

            intervaloTempos.add(interval);
        }

        return intervaloTempos;
    }

    public static List<LocalDate> getDatesBetween(LocalDateTime localDateTime1Inclusive,
                                                  LocalDateTime localDateTime2Inclusive)
    {
        if(localDateTime1Inclusive.isBefore(localDateTime2Inclusive))
            getDatesBetween(localDateTime2Inclusive, localDateTime1Inclusive);
        List<LocalDate> dates = new ArrayList<>();
        LocalDate start = localDateTime1Inclusive.toLocalDate();
        int days = (int) ChronoUnit.DAYS.between(localDateTime1Inclusive, localDateTime2Inclusive.plusDays(1));
        for(int numDays = 0; numDays < days; numDays++) dates.add(start.plusDays(numDays));
        return dates;
    }

    public static boolean isInRange(LocalDateTime minExclusive, LocalDateTime maxExclusive, LocalDateTime time) {
        return minExclusive.isBefore(time) && maxExclusive.isAfter(time);
    }

    public boolean intersects(IntervaloTempo otherInterval){
        return isInRange(this.inicio, this.fim, otherInterval.getInicio())
                || isInRange(this.inicio, this.fim, otherInterval.getFim())
                || isInRange(otherInterval.getInicio(), otherInterval.getFim(), this.inicio)
                || isInRange(otherInterval.getInicio(), otherInterval.getFim(), this.fim)
                || hasSameStartAndEnd(otherInterval);
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean hasSameStartAndEnd(IntervaloTempo a){
       return a.getInicio().equals(this.inicio)
               && a.getFim().equals(this.fim);
    }

    public IntervaloTempo(LocalDateTime inicio, LocalDateTime fim) {
        this.inicio = inicio;
        this.fim = fim;
        this.disponivel = false;
    }

}
