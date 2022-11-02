package org.somevand.fpt.teachung.uebung._04.birds.b;

public class Eagle extends Bird implements FlyingEntity {

    private final FlyBehaviour flyBehaviour = new SoaringFlight(322);

    public Eagle(String name, int age, float weight) {
        super(name, age, weight);
    }

    @Override
    public void eatFood() {
        System.out.println("I eat small mammals");
    }

    @Override
    public FlyBehaviour getFlyBehaviour() {
        return flyBehaviour;
    }
}
