package org.somevand.fpt.teaching.uebung._09.abstractfactory;

public abstract class CarFactory {
    public final Car order(String model) {
        Car car;
        do {
            car = build(model);
            car.fuelUp();
            // if the car fails the test drive, dispose of it and build a new one
        } while (!car.testDrive());
        return car;
    }

    protected abstract Car build(String model);
}
