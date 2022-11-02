package org.somevand.fpt.teachung.uebung._04.points.c;

public class Main {

    public static void main(String[] args) {

        Point p = new Point(1, 2);
        Point cp = new ColorPoint(1, 2, Color.BLUE);
        Point ctp = new CounterPoint(1, 2);

        System.out.printf("p.equals(cp):    %b%n", p.equals(cp));
        System.out.printf("cp.equals(p):    %b%n", cp.equals(p));
        System.out.printf("ctp.equals(p):   %b%n", ctp.equals(p));
    }
}
