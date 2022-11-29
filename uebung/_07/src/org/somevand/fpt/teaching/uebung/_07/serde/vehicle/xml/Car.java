package org.somevand.fpt.teaching.uebung._07.serde.vehicle.xml;

import java.io.Serial;
import java.util.List;

public class Car extends Vehicle {
    @Serial
    private static final long serialVersionUID = 0L;

    private String model;
    private Engine engine;
    private List<Seat> seats;
    private List<Wheel> wheels;

    public Car() {

    }

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

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setSeats(int index, Seat seat) {
        this.seats.set(index, seat);
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public void setWheels(int index, Wheel wheel) {
        this.wheels.set(index, wheel);
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
