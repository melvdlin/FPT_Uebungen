package org.somevand.fpt.teaching.uebung._2.inheritance;

public class Eagle extends FlyingBird {

    public Eagle(String name, int age, float weight, float maxAirSpeed) {
        super(name, age, weight, maxAirSpeed);
    }

    @Override
    public void fly() {
        System.out.println("I am soaring through the sky");
    }

    @Override
    public void liftOff() {
        System.out.println("I am lifting off gracefully");
    }

    @Override
    public void land() {
        System.out.println("I am landing gracefully");
    }

    @Override
    public void eatFood() {
        System.out.println("I eat small mammals");
    }
}
