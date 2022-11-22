package org.somevand.fpt.teaching.uebung.old._06.beans;

import org.somevand.fpt.teaching.uebung.old._06.Car;
import org.somevand.fpt.teaching.uebung.old._06.Seat;
import org.somevand.fpt.teaching.uebung.old._06.Wheel;

import java.io.Serializable;

public class CarBean implements Serializable {
    private int id;
    private String model;
    private EngineBean engine;
    private Seat[] seats;
    private Wheel[] wheels;

    public CarBean() {

    }

    public CarBean(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.engine = new EngineBean(car.getEngine());
        this.seats = car.getSeatList().toArray(new Seat[0]);
        this.wheels = car.getWheelList().toArray(new Wheel[0]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public EngineBean getEngine() {
        return engine;
    }

    public void setEngine(EngineBean engine) {
        this.engine = engine;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public void setSeats(int index, Seat seat) {
        this.seats[index] = seat;
    }

    public Wheel[] getWheels() {
        return wheels;
    }

    public void setWheels(Wheel[] wheels) {
        this.wheels = wheels;
    }

    public void setWheels(int indes, Wheel wheel) {
        this.wheels[indes] = wheel;
    }
}
