package org.somevand.fpt.teachung.uebung._04.birds.b;

public class Seagull extends Bird implements FlyingEntity {

    private final FlyBehaviour flyBehaviour = new SoaringFlight(45);

    public Seagull(String name, int age, float weight) {
        super(name, age, weight);
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
    }

    @Override
    public FlyBehaviour getFlyBehaviour() {
        return flyBehaviour;
    }
}
