package org.somevand.fpt.teaching.uebung._2;

public interface FlyBehavior {
    public float getMaxAirVelocity();
    public void setMaxAirVelocity(float maxAirVelocity);
    public void liftOff();
    public void land();
    public void fly();
}
