package org.somevand.fpt.teaching.uebung._2.composition;

import org.somevand.fpt.teaching.uebung._2.Bird;

public class Penguin extends Bird implements SwimBehaviour {
    private float maxWaterSpeed;

    public Penguin(String name, int age, float weight, float maxWaterSpeed) {
        super(name, age, weight);
        this.maxWaterSpeed = maxWaterSpeed;
    }

    @Override
    public float getMaxWaterSpeed() {
        return maxWaterSpeed;
    }

    @Override
    public void setMaxWaterSpeed(float maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
    }

    @Override
    public void jumpIntoWater() {
        System.out.println("I am jumping into the water like a penguin");
    }

    @Override
    public void leaveWater() {
        System.out.println("I am leaving the water like a penguin");
    }

    @Override
    public void swim() {
        System.out.println("I am swimming like a penguin");
    }
}
