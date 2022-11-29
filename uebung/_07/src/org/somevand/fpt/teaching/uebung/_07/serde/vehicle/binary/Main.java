package org.somevand.fpt.teaching.uebung._07.serde.vehicle.binary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        String path = "data";

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
            var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(car);
        }
        try(var fis = new FileInputStream(path);
            var ois = new ObjectInputStream(fis)) {
            anotherCar = (Vehicle) ois.readObject();
        }

        System.out.println(car);
        System.out.println(anotherCar);

        if (anotherCar != null) {
            System.out.println(car.toString().equals(anotherCar.toString()));
        }
    }
}
