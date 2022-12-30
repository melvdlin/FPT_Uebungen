package org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.*;

import java.util.List;

public class Porsche991 implements Car {

    private Engine engine;
    private List<Seat> seats;
    private HeadLights headLights;
    private Color color;

    public Porsche991(Engine engine, List<Seat> seats, HeadLights headLights, Color color) {
        this.engine = engine;
        this.seats = seats;
        this.headLights = headLights;
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

    @Override
    public String toString() {
        return "Porsche991{" +
                "engine=" + engine +
                ", seats=" + seats +
                ", headLights=" + headLights +
                ", color=" + color +
                '}';
    }
}
