package org.somevand.fpt.teaching.uebung._07.serde.vehicle.binary;

import java.io.Serializable;

public class Seat implements Serializable {
    private Color color;

    public Seat(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "color=" + color +
                '}';
    }
}
