package org.somevand.fpt.teaching.uebung._02.d;

public class Penguin extends Bird {
    private float maxWaterSpeed;

    public Penguin(String name, int age, float weight, float maxWaterSpeed) {
        super(name, age, weight, 0.0F);
        this.maxWaterSpeed = maxWaterSpeed;
    }

    public float getMaxWaterSpeed() {
        return maxWaterSpeed;
    }

    public void setMaxWaterSpeed(float maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
    }

    @Override
    public void fly() {
        throw new RuntimeException("I can't do that!");
    }

    @Override
    public void liftOff() {
        throw new RuntimeException("I can't do that!");
    }

    @Override
    public void land() {
        throw new RuntimeException("I can't do that!");
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
    }

    public void jumpIntoWater() {
        System.out.println("I am jumping into the water like a penguin");
    }

    public void leaveWater() {
        System.out.println("I am leaving the water like a penguin");
    }

    public void swim() {
        System.out.println("I am swimming like a penguin");
    }
}
