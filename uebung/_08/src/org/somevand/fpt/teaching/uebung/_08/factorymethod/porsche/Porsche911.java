package org.somevand.fpt.teaching.uebung._08.factorymethod.porsche;

import org.somevand.fpt.teaching.uebung._08.factorymethod.*;

import java.util.Arrays;
import java.util.List;

public class Porsche911 implements Car {

    private Engine engine = new PetrolEngine(13);
    private List<Seat> seats = Arrays.asList(
            new LeatherCoveredSeat(Color.WHITE),
            new LeatherCoveredSeat(Color.WHITE)
    );
    private HeadLights headLights = new HalogenHeadlights();
    private Color color = Color.RED;

    public Porsche911() {

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
