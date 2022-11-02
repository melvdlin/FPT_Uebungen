package org.somevand.fpt.teachung.uebung._04.birds.a;

public interface FlyBehaviour {
    float getMaxAirSpeed();

    void setMaxAirSpeed(float maxAirSpeed);

    void fly();

    void liftOff();

    void land();
}
