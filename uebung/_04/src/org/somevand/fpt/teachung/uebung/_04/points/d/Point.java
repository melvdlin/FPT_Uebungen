package org.somevand.fpt.teachung.uebung._04.points.d;

import java.util.Objects;

public class Point {
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
        if (o == null) return false;

        return this
                == o
                || o instanceof Point other
                && x == other.getX()
                && y == other.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
