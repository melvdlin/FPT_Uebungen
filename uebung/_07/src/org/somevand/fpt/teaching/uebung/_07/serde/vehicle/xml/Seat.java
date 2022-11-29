package org.somevand.fpt.teaching.uebung._07.serde.vehicle.xml;

import java.io.Serializable;

public class Seat implements Serializable {
    private Color color;

    public Seat() {

    }

    public Seat(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "color=" + color +
                '}';
    }
}
