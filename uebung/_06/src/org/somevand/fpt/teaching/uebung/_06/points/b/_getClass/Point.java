package org.somevand.fpt.teaching.uebung._06.points.b._getClass;

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
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Point other = (Point) o;

        return x == other.x && y == other.y;
    }
}
