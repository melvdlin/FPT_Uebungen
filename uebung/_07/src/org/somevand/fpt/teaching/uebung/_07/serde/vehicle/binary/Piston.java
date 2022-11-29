package org.somevand.fpt.teaching.uebung._07.serde.vehicle.binary;

import java.io.Serial;
import java.io.Serializable;

public class Piston implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private double thickness;

    public Piston(double thickness) {
        this.thickness = thickness;
    }

    public double getThickness() {
        return thickness;
    }

    @Override
    public String toString() {
        return "Piston{" +
                "thickness=" + thickness +
                '}';
    }
}
