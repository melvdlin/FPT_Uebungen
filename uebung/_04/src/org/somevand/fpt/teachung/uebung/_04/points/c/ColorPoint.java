package org.somevand.fpt.teachung.uebung._04.points.c;

import java.util.Objects;

public class ColorPoint extends Point {

    private final Color color;
    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
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
                && super.equals(other)
                && color.equals(other.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
