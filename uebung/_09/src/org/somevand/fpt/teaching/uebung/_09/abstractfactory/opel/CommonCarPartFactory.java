package org.somevand.fpt.teaching.uebung._09.abstractfactory.opel;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.*;

public class CommonCarPartFactory implements CarPartFactory {

    public CommonCarPartFactory() {

    }

    @Override
    public Engine buildEngine() {
        return new DieselEngine(4);
    }

    @Override
    public Seat buildSeat(Color color) {
        return new ClothCoveredSeat(color);
    }
}
