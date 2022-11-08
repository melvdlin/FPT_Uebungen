package org.somevand.fpt.teachung.uebung._04.birds.b;

public class Eagle extends Bird implements FlyBehaviour {
    private float maxAirSpeed;

    public Eagle(String name, int age, float weight, float maxAirSpeed) {
        super(name, age, weight);
        this.maxAirSpeed = maxAirSpeed;
    }

    @Override
    public float getMaxAirSpeed() {
        return maxAirSpeed;
    }

    @Override
    public void setMaxAirSpeed(float maxAirSpeed) {
        this.maxAirSpeed = maxAirSpeed;
    }

    @Override
    public void fly() {
        System.out.println("I am soaring through the sky");
    }

    @Override
    public void liftOff() {
        System.out.println("I am lifting off gracefully");
    }

    @Override
    public void land() {
        System.out.println("I am landing gracefully");
    }

    @Override
    public void eatFood() {
        System.out.println("I eat small mammals");
    }
}
