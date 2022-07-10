package org.somevand.fpt.teaching.uebung._2;

public abstract class FlyingBird extends Bird implements FlyBehavior {
    private float maxAirVelocity;

    public FlyingBird(String name, int age, float weight, float maxAirVelocity) {
        super(name, age, weight);
        this.maxAirVelocity = maxAirVelocity;
    }

    @Override
    public float getMaxAirVelocity() {
        return maxAirVelocity;
    }

    @Override
    public void setMaxAirVelocity(float maxAirVelocity) {
        this.maxAirVelocity = maxAirVelocity;
    }
}
