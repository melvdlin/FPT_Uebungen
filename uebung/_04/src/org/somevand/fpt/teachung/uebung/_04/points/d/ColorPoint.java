package org.somevand.fpt.teachung.uebung._04.points.d;

import java.util.Objects;

public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        return this
                == o
                || o instanceof ColorPoint other
                && point.equals(other.getPoint())
                && color.equals(other.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
