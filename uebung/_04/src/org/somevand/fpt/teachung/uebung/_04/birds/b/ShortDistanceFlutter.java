package org.somevand.fpt.teachung.uebung._04.birds.b;

public class ShortDistanceFlutter extends AbstractFlyBehaviour {


    public ShortDistanceFlutter(float maxAirSpeed) {
        setMaxAirSpeed(maxAirSpeed);
    }

    @Override
    public void fly() {
        System.out.println("I am fluttering towards my target");
    }

    @Override
    public void liftOff() {
        System.out.println("I am jumping off the ground");
    }

    @Override
    public void land() {
        System.out.println("I am landing at my target");
    }
}
