package org.somevand.fpt.teachung.uebung._04.birds.a;

public class Puffin extends FlyingSwimmingBird {

    public Puffin(String name, int age, float weight, float maxAirSpeed, float maxWaterSpeed) {
        super(name, age, weight, maxAirSpeed, maxWaterSpeed);
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish.");
    }

    @Override
    public void fly() {
        System.out.println("I am flying over the water");
    }

    @Override
    public void liftOff() {
        System.out.println("I am lifting off the shore");
    }

    @Override
    public void land() {
        System.out.println("I am landing on the shore");
    }

    @Override
    public void swim() {
        System.out.println("I am swimming through the water");
    }

    @Override
    public void jumpIntoWater() {
        System.out.println("I am diving into the water");
    }

    @Override
    public void leaveWater() {
        System.out.println("I am leaving the water");
    }
}
