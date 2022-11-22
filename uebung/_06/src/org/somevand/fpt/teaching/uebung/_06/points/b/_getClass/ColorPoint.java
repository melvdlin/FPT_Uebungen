package org.somevand.fpt.teaching.uebung._06.points.b._getClass;

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
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        ColorPoint other = (ColorPoint) o;

        return super.equals(other) && color.equals(other.color);
    }
}
