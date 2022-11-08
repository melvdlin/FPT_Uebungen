package org.somevand.fpt.teachung.uebung._04.birds.c;

public class Puffin extends Bird {

    public Puffin(String name, int age, float weight) {
        super(name, age, weight, new SoaringFlight(88.5F), new UnderwaterFlight(16.1F));
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
    }
}
