package org.somevand.fpt.teaching.uebung._2.composition;

import org.somevand.fpt.teaching.uebung._2.Bird;

public class Puffin extends Bird implements FlyBehaviour, SwimBehaviour {
    private float maxAirSpeed;
    private float maxWaterSpeed;

    public Puffin(String name, int age, float weight, float maxAirSpeed, float maxWaterSpeed) {
        super(name, age, weight);
        this.maxAirSpeed = maxAirSpeed;
        this.maxWaterSpeed = maxWaterSpeed;
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish.");
    }

    @Override
    public float getMaxAirSpeed() {
        return maxAirSpeed;
    }

    @Override
    public void setMaxAirSpeed(float maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
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
    public float getMaxWaterSpeed() {
        return this.maxWaterSpeed;
    }

    @Override
    public void setMaxWaterSpeed(float maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
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
