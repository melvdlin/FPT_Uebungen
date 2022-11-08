package org.somevand.fpt.teachung.uebung._04.birds.c;

public class PaddlingSwimBehaviour extends AbstractSwimBehaviour {

    public PaddlingSwimBehaviour(float maxWaterSpeed) {
        setMaxWaterSpeed(maxWaterSpeed);
    }

    @Override
    public void swim() {
        System.out.println("I am paddling through the water with my webbed feet");
    }

    @Override
    public void jumpIntoWater() {
        System.out.println("I am diving off the coastline into the water");
    }

    @Override
    public void leaveWater() {
        System.out.println("I am walking ashore");
    }
}
