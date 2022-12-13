package org.somevand.fpt.teaching.uebung._08.composite.typesafety;

public interface Shape {
    int getX();
    int getY();
    double getSurface();
    void move(int x, int y);
}
