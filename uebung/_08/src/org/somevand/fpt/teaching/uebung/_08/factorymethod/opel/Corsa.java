package org.somevand.fpt.teaching.uebung._08.factorymethod.opel;

import org.somevand.fpt.teaching.uebung._08.factorymethod.*;

import java.util.Arrays;
import java.util.List;

public class Corsa implements Car {

    private Engine engine = new PetrolEngine(3);
    private List<Seat> seats;
    private Color color;

    public Corsa(Color color, Color seatColor) {
        this.color = color;
        this.seats = Arrays.asList(
                new ClothCoveredSeat(Color.BLACK),
                new ClothCoveredSeat(Color.BLACK),
                new ClothCoveredSeat(Color.BLACK),
                new ClothCoveredSeat(Color.BLACK),
                new ClothCoveredSeat(Color.BLACK)
        );
    }

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Corsa{" +
               "engine=" + engine +
               ", seats=" + seats +
               ", color=" + color +
               '}';
    }
}
