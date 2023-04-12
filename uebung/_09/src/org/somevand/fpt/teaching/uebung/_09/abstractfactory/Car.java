package org.somevand.fpt.teaching.uebung._09.abstractfactory;

import java.util.List;
import java.util.Random;

public class Car {

    private Engine engine;
    private List<Seat> seats;
    private Color color;

    public Car(Engine engine, List<Seat> seats, Color color) {
        this.engine = engine;
        this.seats  = seats;
        this.color  = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Color getColor() {
        return color;
    }

    public void fuelUp() {
        System.out.println("Fuelling up...");
    }

    public boolean testDrive() {
        return new Random().nextDouble() <= 0.7;
    }
}
