package org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.*;

public class FancyCarPartFactory implements CarPartFactory {

    @Override
    public Engine buildEngine() {
        return new PetrolEngine(5);
    }

    @Override
    public Seat buildSeat(Color color) {
        return new LeatherCoveredSeat(color);
    }
}
