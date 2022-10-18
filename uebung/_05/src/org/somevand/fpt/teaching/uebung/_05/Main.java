package org.somevand.fpt.teaching.uebung._05;

import org.somevand.fpt.teaching.uebung._05.beans.CarBean;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filenameBinary = "";
        String filenameXML = "";

        Car car = new Car(123, "Opel Corsa", new Engine(100.0D, List.of(
                new Piston(1.0F),
                new Piston(2.0F),
                new Piston(3.0F),
                new Piston(4.0F)
        )), List.of(
                new Seat(Color.RED),
                new Seat(Color.GREEN),
                new Seat(Color.BLUE),
                new Seat(Color.WHITE)
        ), List.of(
                new Wheel(10),
                new Wheel(20),
                new Wheel(30),
                new Wheel(40)
        ));

        Car binaryCar = null;
        Car xmlCar = null;

        try (var bfos = new FileOutputStream(filenameBinary);
             var boos = new ObjectOutputStream(bfos);
             var xfos = new FileOutputStream(filenameXML);
             var xoos = new XMLEncoder(xfos);
             var bfis = new FileInputStream(filenameBinary);
             var bois = new ObjectInputStream(bfis);
             var xfis = new FileInputStream(filenameXML);
             var xois = new XMLDecoder(xfis)) {

            boos.writeObject(car);
            xoos.writeObject(new CarBean(car));

            binaryCar = (Car) bois.readObject();
            xmlCar = new Car((CarBean) xois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
