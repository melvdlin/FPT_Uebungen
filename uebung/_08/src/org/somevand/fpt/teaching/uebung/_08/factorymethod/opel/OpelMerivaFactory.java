package org.somevand.fpt.teaching.uebung._08.factorymethod.opel;

import org.somevand.fpt.teaching.uebung._08.factorymethod.Car;
import org.somevand.fpt.teaching.uebung._08.factorymethod.CarFactory;
import org.somevand.fpt.teaching.uebung._08.factorymethod.Color;

public class OpelMerivaFactory extends CarFactory {

    public OpelMerivaFactory() {

    }

    @Override
    protected Car build(Color carColor, Color seatColor) {
        return new Meriva(carColor, seatColor);
    }
}
