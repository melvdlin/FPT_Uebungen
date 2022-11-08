package org.somevand.fpt.teachung.uebung._04.birds.c;

public class Eagle extends Bird {


    public Eagle(String name, int age, float weight) {
        super(name, age, weight, new SoaringFlight(322), new CannotSwim());
    }

    @Override
    public void eatFood() {
        System.out.println("I eat small mammals");
    }
}
