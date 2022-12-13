package org.somevand.fpt.teaching.uebung._08.composite.uniformity;

import java.util.Collection;

public interface Shape {
    int getX();
    int getY();
    double getSurface();
    void move(int x, int y);
    Collection<Shape> getChildren();
    void add(Shape child);
    void remove(Shape child);
}
