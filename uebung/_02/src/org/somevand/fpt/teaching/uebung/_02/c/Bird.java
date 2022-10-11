package org.somevand.fpt.teaching.uebung._02.c;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getMaxAirSpeed() {
        return maxAirSpeed;
    }

    public void setMaxAirSpeed(float maxAirSpeed) {
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
