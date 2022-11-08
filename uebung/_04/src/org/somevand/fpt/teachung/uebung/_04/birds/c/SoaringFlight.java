package org.somevand.fpt.teachung.uebung._04.birds.c;

public class SoaringFlight extends AbstractFlyBehaviour {

    public SoaringFlight(float maxAirSpeed) {
        setMaxAirSpeed(maxAirSpeed);
    }

    @Override
    public void fly() {
        System.out.println("I am soaring through the air");
    }

    @Override
    public void liftOff() {
        System.out.println("I am gracefully lifting off");
    }

    @Override
    public void land() {
        System.out.println("I am gracefully landing");
    }
}
