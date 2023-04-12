package org.somevand.fpt.teaching.uebung._09.abstractfactory;

import java.util.List;

public class CarFactory {

    private CarPartFactory partFactory;

    public CarFactory(CarPartFactory partFactory) {
        this.partFactory = partFactory;
    }

    public CarPartFactory getPartFactory() {
        return partFactory;
    }

    public void setPartFactory(CarPartFactory partFactory) {
        this.partFactory = partFactory;
    }

    public final Car order(Color carColor, Color seatColor) {
        Car car;
        do {
            car = build(carColor, seatColor);
            car.fuelUp();
            // if the car fails the test drive, dispose of it and build a new one
        } while (!car.testDrive());
        return car;
    }

    private Car build(Color carColor, Color seatColor) {
        return new Car(
                partFactory.buildEngine(),
                List.of(
                        partFactory.buildSeat(seatColor),
                        partFactory.buildSeat(seatColor),
                        partFactory.buildSeat(seatColor),
                        partFactory.buildSeat(seatColor)
                ),
                carColor
        );
    }
}
