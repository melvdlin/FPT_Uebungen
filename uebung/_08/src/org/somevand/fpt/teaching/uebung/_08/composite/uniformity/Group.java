package org.somevand.fpt.teaching.uebung._08.composite.uniformity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Group implements Shape {
    private List<Shape> children = new ArrayList<>();

    @Override
    public int getX() {
        return children.stream().mapToInt(Shape::getX).min().orElse(0);
    }

    @Override
    public int getY() {
        return children.stream().mapToInt(Shape::getY).min().orElse(0);
    }

    @Override
    public double getSurface() {
        return children.stream().mapToDouble(Shape::getSurface).sum();
    }

    @Override
    public void move(int x, int y) {
        children.forEach(child -> child.move(x, y));
    }

    @Override
    public Collection<Shape> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public void add(Shape child) {
        children.add(child);
    }

    @Override
    public void remove(Shape child) {
        children.remove(child);
    }

    @Override
    public String toString() {
        return "Group{" +
                "children=" + children +
                '}';
    }
}
