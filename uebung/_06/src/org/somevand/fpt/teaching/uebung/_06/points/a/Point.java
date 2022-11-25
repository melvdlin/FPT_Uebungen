package org.somevand.fpt.teaching.uebung._06.points.a;

class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        return this
                == o
                || o instanceof Point other
                && x == other.x
                && y == other.y;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(3, 4);
        System.out.printf("p1 == p1:        %b%n", p1 == p1);
        System.out.printf("p1.equals(p1):   %b%n", p1.equals(p1));
        System.out.printf("p1 == p2:        %b%n", p1 == p2);
        System.out.printf("p1.equals(p2):   %b%n", p1.equals(p2));
        System.out.printf("p2.equals(p1):   %b%n", p2.equals(p1));
        System.out.printf("p2 == p3:        %b%n", p2 == p3);
        System.out.printf("p2.equals(p3):   %b%n", p2.equals(p3));
        System.out.printf("p3.equals(p2):   %b%n", p3.equals(p2));
    }
}
