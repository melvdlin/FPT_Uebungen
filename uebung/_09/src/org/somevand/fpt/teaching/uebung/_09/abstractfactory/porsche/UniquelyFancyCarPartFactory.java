package org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.*;

public class UniquelyFancyCarPartFactory implements CarPartFactory {
    private static volatile UniquelyFancyCarPartFactory instance;

    private UniquelyFancyCarPartFactory() {

    }

    public static UniquelyFancyCarPartFactory getInstance() {
        if (instance == null) {
            synchronized (UniquelyFancyCarPartFactory.class) {
                if (instance == null) {
                    instance = new UniquelyFancyCarPartFactory();
                }
            }
        }
        return instance;
    }


    @Override
    public Engine buildEngine(int numberOfPistons) {
        return new SuperAwesomePetrolEngine(numberOfPistons);
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
