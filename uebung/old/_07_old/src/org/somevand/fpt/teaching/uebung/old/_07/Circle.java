package org.somevand.fpt.teaching.uebung.old._07;

public class Circle extends ShapeBase {
    private final double radius;

    public Circle(double radius, int x, int y) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public double surface() {
        return radius * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                "} " + super.toString();
    }
}
