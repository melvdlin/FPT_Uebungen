package org.somevand.fpt.teachung.uebung._04.birds.c;

public class Seagull extends Bird {

    public Seagull(String name, int age, float weight) {
        super(name, age, weight, new SoaringFlight(45), new CannotSwim());
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
    }
}
