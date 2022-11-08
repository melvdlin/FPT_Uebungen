package org.somevand.fpt.teachung.uebung._04.birds.c;

public class Penguin extends Bird {

    public Penguin(String name, int age, float weight) {
        super(name, age, weight, new CannotFly(), new PaddlingSwimBehaviour(9));
    }

    @Override
    public void eatFood() {
        System.out.println("I eat fish");
    }
}
