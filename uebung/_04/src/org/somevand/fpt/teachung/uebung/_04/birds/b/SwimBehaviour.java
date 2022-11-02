package org.somevand.fpt.teachung.uebung._04.birds.b;

public interface SwimBehaviour {
    float getMaxWaterSpeed();

    void setMaxWaterSpeed(float maxWaterSpeed);

    void swim();

    void jumpIntoWater();

    void leaveWater();
}
