package org.somevand.fpt.teachung.uebung._04.birds.b;

public class Penguin extends Bird implements SwimmingEntity {

    private final SwimBehaviour swimBehaviour = new PaddlingSwimBehaviour(9);

    public Penguin(String name, int age, float weight) {
        super(name, age, weight);
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
    }

    @Override
    public SwimBehaviour getSwimBehaviour() {
        return swimBehaviour;
    }
}
