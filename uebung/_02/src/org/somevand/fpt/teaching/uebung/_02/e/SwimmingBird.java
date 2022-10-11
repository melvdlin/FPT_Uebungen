package org.somevand.fpt.teaching.uebung._02.e;

public abstract class SwimmingBird extends Bird {
    private float maxWaterSpeed;

    public SwimmingBird(String name, int age, float weight, float maxWaterSpeed) {
        super(name, age, weight);
        this.maxWaterSpeed = maxWaterSpeed;
    }

    public float getMaxWaterSpeed() {
        return maxWaterSpeed;
    }

    public void setMaxWaterSpeed(float maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
    }

    public abstract void swim();

    public abstract void jumpIntoWater();

    public abstract void leaveWater();
}
