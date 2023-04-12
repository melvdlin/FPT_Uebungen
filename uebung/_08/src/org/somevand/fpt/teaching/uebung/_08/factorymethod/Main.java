package org.somevand.fpt.teaching.uebung._08.factorymethod;

import org.somevand.fpt.teaching.uebung._08.factorymethod.opel.OpelCorsaFactory;
import org.somevand.fpt.teaching.uebung._08.factorymethod.opel.OpelMerivaFactory;
import org.somevand.fpt.teaching.uebung._08.factorymethod.porsche.Porsche911Factory;
import org.somevand.fpt.teaching.uebung._08.factorymethod.porsche.Porsche991Factory;

public class Main {

    public static void main(String[] args) {
        CarFactory corsaFactory = new OpelCorsaFactory();
        CarFactory merivaFactory = new OpelMerivaFactory();
        CarFactory p911Factory = new Porsche911Factory();
        CarFactory p991Factory = new Porsche991Factory();

        System.out.printf(
                "Opel Corsa:  %s%n",
                corsaFactory.order(Color.BLUE, Color.GREY));
        System.out.printf(
                "Opel Meriva: %s%n",
                merivaFactory.order(Color.GREEN, Color.RED));
        System.out.printf(
                "Porsche 911: %s%n",
                p911Factory.order(Color.WHITE, Color.BLACK));
        System.out.printf(
                "Porsche 991: %s%n",
                p991Factory.order(Color.YELLOW, Color.BLUE));
    }
}
