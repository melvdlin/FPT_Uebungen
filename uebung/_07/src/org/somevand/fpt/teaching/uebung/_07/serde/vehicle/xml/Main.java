package org.somevand.fpt.teaching.uebung._07.serde.vehicle.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        String path = "data.xml";

        Vehicle car = new Car(
                3,
                "Corsa",
                new Engine(
                        200.0,
                        new ArrayList<>(List.of(
                                new Piston(500.0),
                                new Piston(-3.1415)))),
                new ArrayList<>(List.of(
                        new Seat(Color.BLACK),
                        new Seat(Color.BLUE))),
                new ArrayList<>(List.of(
                        new Wheel(3),
                        new Wheel(4),
                        new Wheel(5)))
        );
        Vehicle anotherCar = null;

        try(var fos = new FileOutputStream(path);
            var oos = new XMLEncoder(fos)) {
            oos.writeObject(car);
        }
        try(var oos = new XMLEncoder(System.out)) {
            oos.writeObject(car);
        }
        try(var fis = new FileInputStream(path);
            var ois = new XMLDecoder(fis)) {
            anotherCar = (Vehicle) ois.readObject();
        }

        System.out.println(car);
        System.out.println(anotherCar);

        if (anotherCar != null) {
            System.out.println(car.toString().equals(anotherCar.toString()));
        }
    }
}
