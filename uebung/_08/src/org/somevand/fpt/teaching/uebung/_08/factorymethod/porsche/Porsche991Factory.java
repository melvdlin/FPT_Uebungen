package org.somevand.fpt.teaching.uebung._08.factorymethod.porsche;

import org.somevand.fpt.teaching.uebung._08.factorymethod.Car;
import org.somevand.fpt.teaching.uebung._08.factorymethod.CarFactory;
import org.somevand.fpt.teaching.uebung._08.factorymethod.Color;

public class Porsche991Factory extends CarFactory {

    public Porsche991Factory() {

    }

    @Override
    protected Car build(Color carColor, Color seatColor) {
        return new Porsche991(carColor, seatColor);
    }
}
