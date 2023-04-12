package org.somevand.fpt.teaching.uebung._09.abstractfactory;

import org.somevand.fpt.teaching.uebung._09.abstractfactory.opel.CommonCarPartFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche.FancyCarPartFactory;
import org.somevand.fpt.teaching.uebung._09.abstractfactory.porsche.UniquelyFancyCarPartFactory;

public class Main {

    CarPartFactory opelPartFactory = new CommonCarPartFactory();
    CarPartFactory porschePartFactory = new FancyCarPartFactory();
    CarPartFactory ferrariPartFactory = UniquelyFancyCarPartFactory.getInstance();

    CarFactory opelFactory = new CarFactory(opelPartFactory);
    CarFactory porscheFactory = new CarFactory(porschePartFactory);
    CarFactory ferrariFactory = new CarFactory(ferrariPartFactory);

    Car opelCar = opelFactory.order(Color.BLUE, Color.GREY);
    Car porscheCar = porscheFactory.order(Color.WHITE, Color.BLACK);
    Car ferrari = ferrariFactory.order(Color.RED, Color.BROWN);
}
