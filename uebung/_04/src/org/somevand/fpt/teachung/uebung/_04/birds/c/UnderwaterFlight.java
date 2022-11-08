package org.somevand.fpt.teachung.uebung._04.birds.c;

public class UnderwaterFlight extends AbstractSwimBehaviour {

    public UnderwaterFlight(float maxWaterSpeed) {
        setMaxWaterSpeed(maxWaterSpeed);
    }

    @Override
    public void swim() {
        System.out.println("I am beating my wings underwater");
    }

    @Override
    public void jumpIntoWater() {
        System.out.println("I am diving out of the air into the water");
    }

    @Override
    public void leaveWater() {
        System.out.println("I am surfacing");
    }
}
