package org.somevand.fpt.teaching.uebung._08.factorymethod.opel;

import org.somevand.fpt.teaching.uebung._08.factorymethod.*;

import java.util.Arrays;
import java.util.List;

public class Meriva implements Car {

    private Engine engine = new DieselEngine(4);
    private List<Seat> seats;
    private Color color;

    public Meriva(Color carColor, Color seatColor) {
        this.color = carColor;
        this.seats = Arrays.asList(
                new ClothCoveredSeat(seatColor),
                new ClothCoveredSeat(seatColor),
                new ClothCoveredSeat(seatColor),
                new ClothCoveredSeat(seatColor),
                new ClothCoveredSeat(seatColor)
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
        return "Meriva{" +
               "engine=" + engine +
               ", seats=" + seats +
               ", color=" + color +
               '}';
    }
}
