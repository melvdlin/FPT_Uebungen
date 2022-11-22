package org.somevand.fpt.teaching.uebung.old._06;

import java.util.List;

public class Main {

    public static void main(String[] args) {
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

        try {
            var clonedCar = car.clone();
            var copiedCar = new Car(car);

            System.out.println("car == car.clone():                 " + (car == clonedCar));
            System.out.println("car == new Car(car):                " + (car == copiedCar));
            System.out.println("car.clone() == new Car(car):        " + (clonedCar == copiedCar));
            System.out.println("car.equals(car.clone()):            " + car.equals(clonedCar));
            System.out.println("car.equals(new Car(car)):           " + car.equals(copiedCar));
            System.out.println("car.clone().equals(new Car(car)):   " + clonedCar.equals(copiedCar));
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
