package org.somevand.fpt.teaching.uebung._08.factorymethod;

public abstract class CarFactory {

    public final Car order(Color carColor, Color seatColor) {
        Car car;
        do {
            car = build(carColor, seatColor);
            car.fuelUp();
            // if the car fails the test drive, dispose of it and build a new one
        } while (!car.testDrive());
        return car;
    }

    protected abstract Car build(Color carColor, Color seatColor);
}
