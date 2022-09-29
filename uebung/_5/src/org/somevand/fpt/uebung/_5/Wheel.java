package org.somevand.fpt.uebung._5;

import java.io.*;

public class Wheel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int diameter;

    public Wheel(int diameter) {
        this.diameter = diameter;
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
}
