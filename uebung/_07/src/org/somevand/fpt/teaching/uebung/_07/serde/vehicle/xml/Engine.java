package org.somevand.fpt.teaching.uebung._07.serde.vehicle.xml;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Engine implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private double size;
    private List<Piston> pistons;

    public Engine() {

    }

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

    public void setSize(double size) {
        this.size = size;
    }

    public void setPistons(List<Piston> pistons) {
        this.pistons = pistons;
    }

    public void setPistons(int index, Piston piston) {
        this.pistons.set(index, piston);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "size=" + size +
                ", pistons=" + pistons +
                '}';
    }
}
