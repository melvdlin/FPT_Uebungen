package org.somevand.fpt.uebung._7;

public abstract class ShapeBase implements Shape {
    private int x, y;

    protected ShapeBase(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return "ShapeBase{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
