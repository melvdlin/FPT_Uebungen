package org.somevand.fpt.teaching.uebung._08.factorymethod.porsche;

import org.somevand.fpt.teaching.uebung._08.factorymethod.*;

import java.util.Arrays;
import java.util.List;

public class Porsche991 implements Car {

    private Engine engine = new PetrolEngine(17);
    private List<Seat> seats;
    private Color color;

    public Porsche991(Color carColor, Color seatColor) {
        this.color = carColor;
        this.seats = Arrays.asList(new LeatherCoveredSeat(Color.RED));
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
        return "Porsche991{" +
               "engine=" + engine +
               ", seats=" + seats +
               ", color=" + color +
               '}';
    }
}
