package org.somevand.fpt.teaching.uebung._09.abstractfactory;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.opel.CommonCarPartFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche.FancyCarPartFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche.UniquelyFancyCarPartFactory;

public class Main {

    public static void main(String[] args) {
        CarPartFactory opelPartFactory = new CommonCarPartFactory();
        CarPartFactory porschePartFactory = new FancyCarPartFactory();
        CarPartFactory ferrariPartFactory = UniquelyFancyCarPartFactory.getInstance();

        CarDealership opelFactory = new CarDealership(opelPartFactory);
        CarDealership porscheFactory = new CarDealership(porschePartFactory);
        CarDealership ferrariFactory = new CarDealership(ferrariPartFactory);

        Car opelCar = opelFactory.order(Color.BLUE, Color.GREY);
        Car porscheCar = porscheFactory.order(Color.WHITE, Color.BLACK);
        Car ferrariCar = ferrariFactory.order(Color.RED, Color.BROWN);

        System.out.printf("Opel seats:      %s%n", opelCar.getSeats());
        System.out.printf("Porsche Engine:  %s%n", porscheCar.getEngine());
        System.out.printf("Ferrari Engine:  %s%n", ferrariCar.getEngine());
    }

}
