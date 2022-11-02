package org.somevand.fpt.teachung.uebung._04.points.d;

public class Main {

    public static void main(String[] args) {

        Point p = new Point(1, 2);
        ColorPoint cp1 = new ColorPoint(1, 2, Color.BLUE);
        ColorPoint cp2 = new ColorPoint(1, 2, Color.BLUE);
        ColorPoint cp3 = new ColorPoint(2, 3, Color.BLUE);
        ColorPoint cp4 = new ColorPoint(1, 2, Color.RED);
        Point ctp = new CounterPoint(1, 2);

        System.out.printf("p.equals(cp1):               %b%n", p.equals(cp1));
        System.out.printf("p.equals(cp1.getPoint()):    %b%n", p.equals(cp1.getPoint()));
        System.out.printf("p.equals(ctp):               %b%n", p.equals(ctp));
        System.out.printf("cp1.equals(cp2):             %b%n", cp1.equals(cp2));
        System.out.printf("cp1.equals(cp3):             %b%n", cp1.equals(cp3));
        System.out.printf("cp1.equals(cp4):             %b%n", cp1.equals(cp4));
    }
}
