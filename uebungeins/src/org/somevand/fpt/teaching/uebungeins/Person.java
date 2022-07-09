package org.somevand.fpt.teaching.uebungeins;

public class Person {
    private final int age;
    private final String firstName, lastName;
    private final double height;

    public Person(int age, String firstName, String lastName, double height) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }

    public Person() {
        this(22, "Max", "Mustermann", 1.90);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + age + " years old, " + height + "m tall";
    }

    public static void main(String[] args) {
        Person maxMustermann = new Person();
        Person maxPlanck = new Person(89, "Max", "Planck", 6.63);
        Person adaLovelace = new Person(36, "Ada", "Lovelace", 1.65);

        System.out.println(maxMustermann);
        System.out.println(maxPlanck);
        System.out.println(adaLovelace);
    }
}
