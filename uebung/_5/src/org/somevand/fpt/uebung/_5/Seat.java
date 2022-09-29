package org.somevand.fpt.uebung._5;

import java.io.*;

public class Seat implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Color color;

    public Seat(Color color) {
        this.color = color;
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
}
