package org.somevand.fpt.uebung._7;

public class Main {

    public static void main(String[] args) {
        Shape c1 = new Circle(5.0, 15, 45);
        Shape c2 = new Circle(7.8, 3, 24);
        Shape r1 = new Rectangle(5.0, 8.0, 12, 45);
        Shape r2 = new Rectangle(7.0, 4.0, 3, 1);
        Shape group = new Group(c1, c2, r1, r2);

        System.out.println(group);
        group.move(2, 2);
        System.out.println(group);
        group.move(-10, 5);
        System.out.println(group);
    }
}
