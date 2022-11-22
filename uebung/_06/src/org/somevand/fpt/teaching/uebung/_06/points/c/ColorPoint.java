package org.somevand.fpt.teaching.uebung._06.points.c;

import java.util.Objects;

class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        return this
                == o
                || o instanceof ColorPoint other
                && point.equals(other.getPoint())
                && color.equals(other.getColor());
    }
}
