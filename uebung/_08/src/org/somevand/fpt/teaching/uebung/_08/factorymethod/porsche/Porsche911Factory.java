package org.somevand.fpt.teaching.uebung._08.factorymethod.porsche;

import org.somevand.fpt.teaching.uebung._08.factorymethod.Car;
import org.somevand.fpt.teaching.uebung._08.factorymethod.CarFactory;
import org.somevand.fpt.teaching.uebung._08.factorymethod.Color;

public class Porsche911Factory extends CarFactory {

    public Porsche911Factory() {
        
    }

    @Override
    protected Car build(Color carColor, Color seatColor) {
        return new Porsche911(carColor, seatColor);
    }
}
