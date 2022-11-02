package org.somevand.fpt.teachung.uebung._04.birds.a;

public interface SwimBehaviour {
    float getMaxWaterSpeed();

    void setMaxWaterSpeed(float maxWaterSpeed);

    void swim();

    void jumpIntoWater();

    void leaveWater();
}
