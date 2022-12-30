package org.somevand.fpt.teaching.uebung._09.abstractfactory.opel;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.*;

public class CommonCarPartFactory implements CarPartFactory {

    public CommonCarPartFactory() {

    }

    @Override
    public Engine buildEngine(int numberOfPistons) {
        return numberOfPistons < 5 ? new PetrolEngine(numberOfPistons) : new DieselEngine(numberOfPistons);
    }

    @Override
    public HeadLights buildHeadlights() {
        return new HalogenHeadlights();
    }

    @Override
    public Seat buildSeat(Color color) {
        return new ClothCoveredSeat(color);
    }
}
