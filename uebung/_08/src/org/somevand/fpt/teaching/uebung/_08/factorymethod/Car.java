package org.somevand.fpt.teaching.uebung._08.factorymethod;

import java.util.List;
import java.util.Random;

public interface Car {

    Engine getEngine();

    List<Seat> getSeats();

    Color getColor();

    default void fuelUp() {
        System.out.println("Fuelling up...");
    }

    default boolean testDrive() {
        return new Random().nextDouble() <= 0.7;
    }
}
