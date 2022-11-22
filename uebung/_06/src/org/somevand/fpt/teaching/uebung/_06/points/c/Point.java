package org.somevand.fpt.teaching.uebung._06.points.c;

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
                && x == other.getX()
                && y == other.getY();
    }
}
