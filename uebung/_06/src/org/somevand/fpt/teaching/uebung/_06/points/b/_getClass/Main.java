package org.somevand.fpt.teaching.uebung._06.points.b._getClass;

class Main {

    public static void main(String[] args) {

        Point p = new Point(1, 2);
        Point cp = new ColorPoint(1, 2, Color.BLUE);
        Point ctrp = new CounterPoint(1, 2);

        System.out.printf("p.equals(cp):    %b%n", p.equals(cp));
        System.out.printf("cp.equals(p):    %b%n", cp.equals(p));
        System.out.printf("p.equals(ctrp):  %b%n", p.equals(ctrp));
        System.out.printf("ctrp.equals(p):  %b%n", ctrp.equals(p));
        System.out.printf("cp.equals(ctrp): %b%n", cp.equals(ctrp));
        System.out.printf("ctrp.equals(cp): %b%n", ctrp.equals(cp));
    }
}
