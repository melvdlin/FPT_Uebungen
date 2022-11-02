package org.somevand.fpt.teachung.uebung._04.points.b;

public class Main {

    public static void main(String[] args) {
        Point  p = new Point(1, 2);
        Point cp = new ColorPoint(1, 2, Color.BLUE);

        System.out.printf("p.equals(cp):    %b%n", p.equals(cp));
        System.out.printf("cp.equals(p):    %b%n", cp.equals(p));
    }
}
