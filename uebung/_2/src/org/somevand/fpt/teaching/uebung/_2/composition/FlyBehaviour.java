package org.somevand.fpt.teaching.uebung._2.composition;

public interface FlyBehaviour {


    float getMaxAirSpeed();

    void setMaxAirSpeed(float maxAirSpeed);

    void fly();

    void liftOff();

    void land();
}
