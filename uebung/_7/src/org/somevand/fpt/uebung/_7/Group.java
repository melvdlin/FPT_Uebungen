package org.somevand.fpt.uebung._7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Group implements Shape {

    private final List<Shape> children;

    public Group(Collection<Shape> children) {
        this.children = new ArrayList<>(children);
    }

    public Group(Group other) {
        this(other.children);
    }

    public Group(Shape... children) {
        this(Arrays.asList(children));
    }

    @Override
    public void move(int x, int y) {
        children.forEach(child -> child.move(x, y));
    }

    @Override
    public double surface() {
        return children.stream().mapToDouble(Shape::surface).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group{children=List{");
        children.forEach(child -> sb.append(child.toString()).append(", "));
        sb.append("}}");
        return sb.toString();
    }
}
