package org.somevand.fpt.teaching.uebung._05;

import java.io.*;

public class Piston implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private float thickness;

    public Piston(float thickness) {
        this.thickness = thickness;
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
}
