package org.somevand.fpt.teaching.uebung._06.points.b._instanceof;

import java.util.Objects;

class ColorPoint extends Point {

    private final Color color;
    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        return this
                == o
                || o instanceof ColorPoint other
                && super.equals(other)
                && color.equals(other.color);
    }
}
