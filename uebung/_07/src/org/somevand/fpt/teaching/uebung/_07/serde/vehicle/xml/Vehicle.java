package org.somevand.fpt.teaching.uebung._07.serde.vehicle.xml;

import java.io.Serial;
import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private int id;

    public Vehicle() {

    }

    public Vehicle(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                '}';
    }
}
