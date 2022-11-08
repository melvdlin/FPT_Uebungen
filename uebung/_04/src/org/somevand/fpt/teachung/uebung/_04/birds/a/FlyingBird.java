package org.somevand.fpt.teachung.uebung._04.birds.a;

public abstract class FlyingBird extends Bird {
    private float maxAirSpeed;

    public FlyingBird(String name, int age, float weight, float maxAirSpeed) {
        super(name, age, weight);
        this.maxAirSpeed = maxAirSpeed;
    }

    public float getMaxAirSpeed() {
        return maxAirSpeed;
    }

    public void setMaxAirSpeed(float maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }

    public abstract void fly();

    public abstract void liftOff();

    public abstract void land();
}
