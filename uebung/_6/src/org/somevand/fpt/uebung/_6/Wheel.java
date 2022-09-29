package org.somevand.fpt.uebung._6;

import com.sun.nio.sctp.NotificationHandler;

import java.io.*;
import java.util.Objects;

public class Wheel implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int diameter;

    public Wheel(int diameter) {
        this.diameter = diameter;
    }

    public Wheel(Wheel other) {
        this.diameter = other.diameter;
    }

    public Wheel() {

    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(diameter);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException {
        this.diameter = ois.readInt();
    }

    @Override
    public Wheel clone() throws CloneNotSupportedException {
        return (Wheel) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return diameter == wheel.diameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diameter);
    }
}
