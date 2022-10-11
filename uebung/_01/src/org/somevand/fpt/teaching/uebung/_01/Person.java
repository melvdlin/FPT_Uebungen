package org.somevand.fpt.teaching.uebung._01;

public class Person {
    private int age;
    private String firstName, lastName;
    private double height;


    public Person() {
        this.age = 0;
        this.firstName = "";
        this.lastName = "";
        this.height = 0.0;
    }

    public Person(int age, String firstName, String lastName, double height) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + age + " years old, " + height + "m tall";
    }

    public static void main(String[] args) {
        Person maxMustermann = new Person(42, "Max", "Mustermann", 1.82);
        Person maxPlanck = new Person(89, "Max", "Planck", 6.63);
        Person adaLovelace = new Person(92, "Karl", "Popper", 1.902);

        System.out.println(maxMustermann);
        System.out.println(maxPlanck);
        System.out.println(adaLovelace);
    }
}
