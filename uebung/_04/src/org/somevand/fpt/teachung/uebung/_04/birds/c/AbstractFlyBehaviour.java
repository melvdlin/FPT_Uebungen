package org.somevand.fpt.teachung.uebung._04.birds.c;

public abstract class AbstractFlyBehaviour implements FlyBehaviour {
    private float maxAirSpeed;

    @Override
    public float getMaxAirSpeed() {
        return maxAirSpeed;
    }

    @Override
    public void setMaxAirSpeed(float maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }
}
