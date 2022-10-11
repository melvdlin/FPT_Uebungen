package org.somevand.fpt.teaching.uebung._02.a;

public class Bird {
    public String name;
    public int age;
    public float weight;
    public float maxAirSpeed;

    public Bird(String name, int age, float weight, float maxAirSpeed) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.maxAirSpeed = maxAirSpeed;
    }

    public void fly() {
        System.out.println("I am flying");
    }

    public void liftOff() {
        System.out.println("I am lifting off");
    }

    public void land() {
        System.out.println("I am landing");
    }

    public void eatFood() {
        System.out.println("I am eating food");
    }
}
