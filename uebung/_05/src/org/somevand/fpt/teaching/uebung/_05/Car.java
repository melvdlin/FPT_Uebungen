package org.somevand.fpt.teaching.uebung._05;

import org.somevand.fpt.teaching.uebung._05.beans.CarBean;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Car implements Vehicle, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;
    private String model;
    private Engine engine;
    private ArrayList<Seat> seatList;
    private ArrayList<Wheel> wheelList;

    private Car() {
        this.seatList = new ArrayList<>();
        this.wheelList = new ArrayList<>();
    }

    public Car(int id, String model, Engine engine, Collection<Seat> seats, Collection<Wheel> wheels) {
        this();
        this.id = id;
        this.model = model;
        this.engine = engine;
        this.seatList.addAll(seats);
        this.wheelList.addAll(wheels);
    }

    public Car(CarBean car) {
        this(
                car.getId(),
                car.getModel(),
                new Engine(car.getEngine().getSize(), Arrays.asList(car.getEngine().getPistons())),
                Arrays.asList(car.getSeats()),
                Arrays.asList(car.getWheels())
        );
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public List<Wheel> getWheelList() {
        return wheelList;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(id);
        oos.writeObject(model);
        oos.writeObject(engine);
        oos.writeObject(seatList);
        oos.writeObject(wheelList);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.id = ois.readInt();
        this.model = (String) ois.readObject();
        this.engine = (Engine) ois.readObject();
        this.seatList = (ArrayList<Seat>) ois.readObject();
        this.wheelList = (ArrayList<Wheel>) ois.readObject();
    }
}
