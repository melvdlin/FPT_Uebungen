package org.somevand.fpt.teaching.uebung._06;

import java.io.*;
import java.util.Objects;

public class Piston implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    private float thickness;

    public Piston(float thickness) {
        this.thickness = thickness;
    }

    public Piston (Piston other) {
        this.thickness = other.thickness;
    }

    public Piston() {

    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeFloat(thickness);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException {
        this.thickness = ois.readFloat();
    }

    @Override
    protected Piston clone() throws CloneNotSupportedException {
        return (Piston) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piston piston = (Piston) o;
        return Float.compare(piston.thickness, thickness) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(thickness);
    }
}
