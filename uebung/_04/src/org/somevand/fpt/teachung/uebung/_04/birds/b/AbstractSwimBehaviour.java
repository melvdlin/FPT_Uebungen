package org.somevand.fpt.teachung.uebung._04.birds.b;

public abstract class AbstractSwimBehaviour implements SwimBehaviour {
    private float maxWaterSpeed;

    @Override
    public float getMaxWaterSpeed() {
        return maxWaterSpeed;
    }

    @Override
    public void setMaxWaterSpeed(float maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
    }
}
