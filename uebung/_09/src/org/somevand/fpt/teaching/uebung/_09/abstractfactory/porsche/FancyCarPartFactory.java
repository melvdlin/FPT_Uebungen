package org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.*;

public class FancyCarPartFactory implements CarPartFactory {

    @Override
    public Engine buildEngine(int numberOfPistons) {
        return new PetrolEngine(numberOfPistons);
    }

    @Override
    public HeadLights buildHeadlights() {
        return new LEDHeadlights();
    }

    @Override
    public Seat buildSeat(Color color) {
        return new LeatherCoveredSeat(color);
    }
}
