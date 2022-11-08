package org.somevand.fpt.teachung.uebung._04.birds.a;

public abstract class FlyingSwimmingBird extends Bird {

    private float maxAirSpeed;
    private float maxWaterSpeed;

    public FlyingSwimmingBird(String name, int age, float weight, float maxAirSpeed, float maxWaterSpeed) {
        super(name, age, weight);
        this.maxAirSpeed = maxAirSpeed;
        this.maxWaterSpeed = maxWaterSpeed;
    }

    public float getMaxAirSpeed() {
        return maxAirSpeed;
    }

    public void setMaxAirSpeed(float maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }

    public float getMaxWaterSpeed() {
        return maxWaterSpeed;
    }

    public void setMaxWaterSpeed(float maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
    }

    public abstract void fly();

    public abstract void liftOff();

    public abstract void land();

    public abstract void swim();

    public abstract void jumpIntoWater();

    public abstract void leaveWater();
}
