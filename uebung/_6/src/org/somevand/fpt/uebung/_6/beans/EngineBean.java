package org.somevand.fpt.uebung._6.beans;

import org.somevand.fpt.uebung._6.Engine;
import org.somevand.fpt.uebung._6.Piston;

import java.io.Serializable;

public class EngineBean implements Serializable {
    private double size;
    private Piston[] pistons;

    public EngineBean() {

    }

    public EngineBean(Engine engine) {
        this.size = engine.getSize();
        this.pistons = engine.getPistonList().toArray(new Piston[0]);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Piston[] getPistons() {
        return pistons;
    }

    public void setPistons(Piston[] pistons) {
        this.pistons = pistons;
    }

    public void setPistons(int index, Piston piston) {
        this.pistons[index] = piston;
    }
}
