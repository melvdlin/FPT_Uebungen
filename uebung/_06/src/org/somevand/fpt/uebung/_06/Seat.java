package org.somevand.fpt.uebung._06;

import java.io.*;
import java.util.Objects;

public class Seat implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Color color;

    public Seat(Color color) {
        this.color = color;
    }

    public Seat(Seat other) {
        this.color = other.color;
    }

    public Seat() {

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(color);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.color = (Color) ois.readObject();
    }

    @Override
    protected Seat clone() throws CloneNotSupportedException {
        return (Seat) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return color == seat.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
