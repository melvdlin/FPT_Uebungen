package org.somevand.fpt.teaching.uebung._08.factorymethod.porsche;

import org.somevand.fpt.teaching.uebung._08.factorymethod.Car;
import org.somevand.fpt.teaching.uebung._08.factorymethod.CarFactory;

public class PorscheFactory extends CarFactory {

    @Override
    protected Car build(String model) {
        return switch (model.toLowerCase()) {
            case "911" -> new Porsche911();
            case "991" -> new Porsche991();
            default -> throw new IllegalArgumentException("Unknown model: " + model);
        };
    }
}
