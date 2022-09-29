package org.somevand.fpt.uebung._7;

public class Rectangle extends ShapeBase {
    private final double width, height;

    public Rectangle(double width, double height, int x, int y) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public double surface() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                "} " + super.toString();
    }
}
