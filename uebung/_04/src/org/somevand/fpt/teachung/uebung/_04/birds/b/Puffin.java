package org.somevand.fpt.teachung.uebung._04.birds.b;

public class Puffin extends Bird implements FlyingEntity, SwimmingEntity {

    private final FlyBehaviour flyBehaviour = new SoaringFlight(88.5F);
    private final SwimBehaviour swimBehaviour = new UnderwaterFlight(16.1F);

    public Puffin(String name, int age, float weight) {
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

    @Override
    public SwimBehaviour getSwimBehaviour() {
        return swimBehaviour;
    }
}
