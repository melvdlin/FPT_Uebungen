package org.somevand.fpt.teaching.uebung._2;

public abstract class SwimmingBird extends Bird implements SwimBehavior {
    private float maxWaterVelocity;

    public SwimmingBird(String name, int age, float weight, float maxWaterVelocity) {
        super(name, age, weight);
        this.maxWaterVelocity = maxWaterVelocity;
    }

    @Override
    public float getMaxWaterVelocity() {
        return maxWaterVelocity;
    }

    @Override
    public void setMaxWaterVelocity(float maxWaterVelocity) {
        this.maxWaterVelocity = maxWaterVelocity;
    }
}
