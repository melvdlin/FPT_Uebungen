package org.somevand.fpt.teaching.uebung._2;

public interface SwimBehavior {
    public float getMaxWaterVelocity();
    public void setMaxWaterVelocity(float maxWaterVelocity);
    public void jumpIntoWater();
    public void leaveWater();
    public void swim();
}
