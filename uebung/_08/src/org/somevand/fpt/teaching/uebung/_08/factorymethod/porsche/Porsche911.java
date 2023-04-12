package org.somevand.fpt.teaching.uebung._08.factorymethod.porsche;

import org.somevand.fpt.teaching.uebung._08.factorymethod.*;

import java.util.Arrays;
import java.util.List;

public class Porsche911 implements Car {

    private Engine engine = new PetrolEngine(13);
    private List<Seat> seats;
    private Color color;

    public Porsche911(Color carColor, Color seatColor) {
        this.color = carColor;
        this.seats = Arrays.asList(
                new LeatherCoveredSeat(seatColor),
                new LeatherCoveredSeat(seatColor)
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
        return "Porsche911{" +
               "engine=" + engine +
               ", seats=" + seats +
               ", color=" + color +
               '}';
    }
}
