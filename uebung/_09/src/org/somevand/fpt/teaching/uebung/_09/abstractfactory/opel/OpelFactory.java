package org.somevand.fpt.teaching.uebung._09.abstractfactory.opel;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.Car;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.CarFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.CarPartFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.Color;

import java.util.List;

public class OpelFactory extends CarFactory {

    private CarPartFactory partFactory;
    private Color color;

    public OpelFactory(CarPartFactory partFactory, Color color) {
        this.partFactory = partFactory;
        this.color = color;
    }

    @Override
    protected Car build(String model) {
        return switch (model.toLowerCase()) {
            case "meriva a" -> new Meriva(
                    partFactory.buildEngine(4),
                    List.of(
                            partFactory.buildSeat(Color.GREY),
                            partFactory.buildSeat(Color.GREY),
                            partFactory.buildSeat(Color.GREY),
                            partFactory.buildSeat(Color.GREY)
                    ), partFactory.buildHeadlights(),
                    color
            );
            case "meriva b" -> new Meriva(
                    partFactory.buildEngine(5),
                    List.of(
                            partFactory.buildSeat(Color.GREY),
                            partFactory.buildSeat(Color.GREY),
                            partFactory.buildSeat(Color.GREY),
                            partFactory.buildSeat(Color.GREY)
                    ), partFactory.buildHeadlights(),
                    color
            );
            case "corsa" -> new Corsa(
                    partFactory.buildEngine(3),
                    List.of(
                            partFactory.buildSeat(Color.BLACK),
                            partFactory.buildSeat(Color.BLACK),
                            partFactory.buildSeat(Color.BLACK),
                            partFactory.buildSeat(Color.BLACK)
                    ), partFactory.buildHeadlights(),
                    color
            );
            default -> throw new IllegalArgumentException("Unknown model: " + model);
        };
    }

    public static void main(String[] args) {
        Car car = new OpelFactory(new CommonCarPartFactory(), Color.BLUE).order("Meriva A");
    }
}
