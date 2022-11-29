package org.somevand.fpt.teaching.uebung._07.serde.vehicle.binary;

import java.io.Serial;
import java.util.List;

public class Car extends Vehicle {
    @Serial
    private static final long serialVersionUID = 0L;

    private String model;
    private Engine engine;
    private List<Seat> seats;
    private List<Wheel> wheels;

    public Car(int id, String model, Engine engine, List<Seat> seats, List<Wheel> wheels) {
        super(id);
        this.model = model;
        this.engine = engine;
        this.seats = seats;
        this.wheels = wheels;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engine=" + engine +
                ", seats=" + seats +
                ", wheels=" + wheels +
                "} " + super.toString();
    }
}
