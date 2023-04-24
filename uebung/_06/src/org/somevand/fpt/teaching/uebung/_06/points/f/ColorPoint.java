package org.somevand.fpt.teaching.uebung._06.points.f;

import java.util.Objects;

class ColorPoint implements Cloneable {

    private Point point;
    private Color color;

    public ColorPoint(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public ColorPoint(ColorPoint other) {
        this.point = other.point;
        this.color = other.color;
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

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + point.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }

    @Override
    public ColorPoint clone() {
        try {
            ColorPoint clone = (ColorPoint) super.clone();
            clone.point = clone.point.clone();
            clone.color = clone.color.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
