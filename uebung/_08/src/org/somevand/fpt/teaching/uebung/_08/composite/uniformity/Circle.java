package org.somevand.fpt.teaching.uebung._08.composite.uniformity;

import java.util.Collection;

public class Circle implements Shape {

    private int x, y, radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public double getSurface() {
        return radius * radius * Math.PI;
    }

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Collection<Shape> getChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Shape child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Shape child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}
