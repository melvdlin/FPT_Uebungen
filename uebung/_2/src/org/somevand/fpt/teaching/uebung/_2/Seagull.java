package org.somevand.fpt.teaching.uebung._2;

public class Seagull extends FlyingBird {

    public Seagull(String name, int age, float weight, float maxAirVelocity) {
        super(name, age, weight, maxAirVelocity);
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
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
    public void fly() {
        System.out.println("I am flying like a seagull");
    }
}
