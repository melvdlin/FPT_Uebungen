package org.somevand.fpt.teachung.uebung._04.birds.c;

public class Chicken extends Bird {

    public Chicken(String name, int age, float weight) {
        super(name, age, weight, new ShortDistanceFlutter(14), new CannotSwim());
    }

    @Override
    public void eatFood() {
        System.out.println("I eat insects and grain");
    }
}
