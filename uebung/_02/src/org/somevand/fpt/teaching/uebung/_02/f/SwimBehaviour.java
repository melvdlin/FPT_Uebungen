package org.somevand.fpt.teaching.uebung._02.f;

public interface SwimBehaviour {
    float getMaxWaterSpeed();

    void setMaxWaterSpeed(float maxWaterSpeed);

    void swim();

    void jumpIntoWater();

    void leaveWater();
}
