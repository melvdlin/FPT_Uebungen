package org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.Car;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.CarFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.CarPartFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.Color;

import java.util.List;

public class PorscheFactory extends CarFactory {

    private CarPartFactory partFactory;

    public PorscheFactory(CarPartFactory partFactory) {
        this.partFactory = partFactory;
    }

    @Override
    protected Car build(String model) {
        return switch (model.toLowerCase()) {
            case "911" -> new Porsche911(
                    partFactory.buildEngine(8),
                    List.of(
                            partFactory.buildSeat(Color.WHITE),
                            partFactory.buildSeat(Color.WHITE)
                    ), partFactory.buildHeadlights(),
                    Color.GREY);
            case "991" -> new Porsche991(
                    partFactory.buildEngine(17),
                    List.of(
                            partFactory.buildSeat(Color.BLACK),
                            partFactory.buildSeat(Color.BLACK)
                    ), partFactory.buildHeadlights(),
                    Color.RED);
            default -> throw new IllegalArgumentException("Unknown model: " + model);
        };
    }
}
