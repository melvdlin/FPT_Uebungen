package org.somevand.fpt.teachung.uebung._04.birds.a;

public class Seagull extends FlyingBird {

    public Seagull(String name, int age, float weight, float maxAirSpeed) {
        super(name, age, weight, maxAirSpeed);
    }

    @Override
    public void fly() {
        System.out.println("I am flying like a seagull");
    }

    @Override
    public void liftOff() {
        System.out.println("I am lifting off like a seagull");
    }

    @Override
    public void land() {
        System.out.println("I am landing like a seagull");
    }

    @Override
    public void eatFood() {
        System.out.println("I am coming for your food");
    }
}
