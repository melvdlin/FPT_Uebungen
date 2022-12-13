package org.somevand.fpt.teaching.uebung._08.factorymethod.opel;

import org.somevand.fpt.teaching.uebung._08.factorymethod.*;

import java.util.Arrays;
import java.util.List;

public class Meriva implements Car {

    private Engine engine;
    private List<Seat> seats = Arrays.asList(
            new ClothCoveredSeat(Color.GREY),
            new ClothCoveredSeat(Color.GREY),
            new ClothCoveredSeat(Color.BLACK),
            new ClothCoveredSeat(Color.BLACK),
            new ClothCoveredSeat(Color.BLACK)
    );

    private HeadLights headLights = new LEDHeadlights();

    private Color color;

    public Meriva(Color color, boolean diesel) {
        this.color = color;
        this.engine = diesel ? new DieselEngine(4) : new PetrolEngine(4);
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
