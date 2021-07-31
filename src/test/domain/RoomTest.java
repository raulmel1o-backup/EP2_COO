package test.domain;

import main.domain.Sala;
import main.infra.exception.NegativeOrZeroCapacityException;

public class RoomTest {
    public static void main(String[] args) {
        Should_Create_New_Room_With_Location();
        Should_Create_New_Room_Without_Location();
        Should_Raise_NegativeCapacityException();
    }

    public static void Should_Create_New_Room_With_Location(){
        final String local = "Massachusetts - USA";
        final String name  = "Recording Studio";
        final String obs   = "Crazy Room from Virtual Insanity";
        final int cap      = 1;
        Sala room = new Sala(name, obs, cap, local);
        if(!room.getLocal().equals("Massachusetts - USA")) System.out.println("Room instantiation went wrong");
    }

    public static void Should_Create_New_Room_Without_Location(){
        final String name  = "Recording Studio";
        final String obs   = "Crazy Room from Virtual Insanity";
        final int cap      = 1;
        Sala room = new Sala(name, obs, cap);
        if(!room.getLocal().equals("")) System.out.println("Room instantiation went wrong");
    }

    public static void Should_Raise_NegativeCapacityException(){
        final String name  = "Recording Studio";
        final String obs   = "Crazy Room from Virtual Insanity";
        final int cap      = -1;
        try {
            Sala room = new Sala(name, obs, cap);
            System.out.println("NegativeCapacityException was not thrown");
        }
        catch (NegativeOrZeroCapacityException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println("Exception caught is not of type NegativeCapacityException");
        }
    }


}
