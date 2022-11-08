package org.somevand.fpt.teachung.uebung._04.birds.c;

public class CannotSwim implements SwimBehaviour {

    @Override
    public float getMaxWaterSpeed() {
        return 0;
    }

    @Override
    public void setMaxWaterSpeed(float maxWaterSpeed) {  }

    @Override
    public void swim() {
        System.out.println("Help, I'm drowing!");
    }

    @Override
    public void jumpIntoWater() {
        System.out.println("I can't swim!");
    }

    @Override
    public void leaveWater() {
        System.out.println("I am no longer drowning");
    }
}
