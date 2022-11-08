package org.somevand.fpt.teachung.uebung._04.birds.c;

public class CannotFly implements FlyBehaviour {

    @Override
    public float getMaxAirSpeed() {
        return 0;
    }

    @Override
    public void setMaxAirSpeed(float maxAirSpeed) { }

    @Override
    public void fly() {
        System.out.println("Help, I'm falling!");
    }

    @Override
    public void liftOff() {
        System.out.println("I can't fly!");
    }

    @Override
    public void land() {
        System.out.println("Ouch!");
    }
}
