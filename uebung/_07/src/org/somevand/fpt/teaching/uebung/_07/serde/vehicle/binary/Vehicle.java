package org.somevand.fpt.teaching.uebung._07.serde.vehicle.binary;

import java.io.Serial;
import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private int id;

    public Vehicle(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                '}';
    }
}
