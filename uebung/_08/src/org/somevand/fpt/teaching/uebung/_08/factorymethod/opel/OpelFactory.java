package org.somevand.fpt.teaching.uebung._08.factorymethod.opel;

import org.somevand.fpt.teaching.uebung._08.factorymethod.Car;
import org.somevand.fpt.teaching.uebung._08.factorymethod.CarFactory;
import org.somevand.fpt.teaching.uebung._08.factorymethod.Color;

public class OpelFactory extends CarFactory {

    private Color color;

    public OpelFactory(Color color) {
        this.color = color;
    }

    @Override
    protected Car build(String model) {
        return switch (model.toLowerCase()) {
            case "meriva a" -> new Meriva(color, false);
            case "meriva b" -> new Meriva(color, true);
            case "corsa" -> new Corsa(color);
            default -> throw new IllegalArgumentException("Unknown model: " + model);
        };
    }

    public static void main(String[] args) {
        Car car = new OpelFactory(Color.BLUE).order("Meriva A");
    }
}
