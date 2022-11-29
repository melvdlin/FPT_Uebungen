package org.somevand.fpt.teaching.uebung._07.serde.vehicle.binary;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Engine implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private double size;
    private List<Piston> pistons;

    public Engine(double size, List<Piston> pistons) {
        this.size = size;
        this.pistons = pistons;
    }

    public double getSize() {
        return size;
    }

    public List<Piston> getPistons() {
        return pistons;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "size=" + size +
                ", pistons=" + pistons +
                '}';
    }
}
