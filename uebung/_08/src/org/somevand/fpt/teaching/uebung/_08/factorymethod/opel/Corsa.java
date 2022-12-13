package org.somevand.fpt.teaching.uebung._08.factorymethod.opel;

import org.somevand.fpt.teaching.uebung._08.factorymethod.*;

import java.util.Arrays;
import java.util.List;

public class Corsa implements Car {

    private Engine engine = new PetrolEngine(3);
    private List<Seat> seats = Arrays.asList(
            new ClothCoveredSeat(Color.BLACK),
            new ClothCoveredSeat(Color.BLACK),
            new ClothCoveredSeat(Color.BLACK),
            new ClothCoveredSeat(Color.BLACK),
            new ClothCoveredSeat(Color.BLACK)
    );
    private HeadLights headLights = new LEDHeadlights();
    private Color color;

    public Corsa(Color color) {
        this.color = color;
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
    public HeadLights getHeadlights() {
        return headLights;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
