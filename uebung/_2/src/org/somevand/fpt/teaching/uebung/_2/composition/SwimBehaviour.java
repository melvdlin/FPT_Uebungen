package org.somevand.fpt.teaching.uebung._2.composition;

public interface SwimBehaviour {

    float getMaxWaterSpeed();

    void setMaxWaterSpeed(float maxWaterSpeed);

    void swim();

    void jumpIntoWater();

    void leaveWater();
}
