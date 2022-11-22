package org.somevand.fpt.teaching.uebung.old._06;

import org.somevand.fpt.teaching.uebung.old._06.beans.CarBean;

import java.io.*;
import java.util.*;

public class Car implements Vehicle, Serializable, Cloneable {
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

    public Car(Car other) {
        this();
        this.id = other.id;
        this.model = other.model;
        this.engine = new Engine(other.engine);
        this.seatList = new ArrayList<>(other.seatList);
        for (int i = 0; i < this.seatList.size(); ++i) {
            this.seatList.set(i, new Seat(this.seatList.get(i)));
        }
        this.wheelList = new ArrayList<>(other.wheelList);
        for (int i = 0; i < this.wheelList.size(); ++i) {
            this.wheelList.set(i, new Wheel(this.wheelList.get(i)));
        }
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

    @Override
    protected Car clone() throws CloneNotSupportedException {
        var clone = (Car) super.clone();
        clone.engine = clone.engine.clone();
        clone.seatList = (ArrayList<Seat>) clone.seatList.clone();
        for (int i = 0; i < clone.seatList.size(); ++i) {
            clone.seatList.set(i, clone.seatList.get(i).clone());
        }
        clone.wheelList = (ArrayList<Wheel>) clone.wheelList.clone();
        for (int i = 0; i < clone.wheelList.size(); ++i) {
            clone.wheelList.set(i, clone.wheelList.get(i).clone());
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Objects.equals(model, car.model) && Objects.equals(engine, car.engine) && Objects.equals(seatList, car.seatList) && Objects.equals(wheelList, car.wheelList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, engine, seatList, wheelList);
    }
}
