package org.somevand.fpt.teaching.uebung._10.producerconsumer.unrestricted;

import org.somevand.fpt.teaching.uebung._10.producerconsumer.DummyCar;

import java.util.List;

public class Producer implements Runnable {

    private final List<DummyCar> cars;
    private final String model;

    public Producer(
            List<DummyCar> cars,
            String model) {
        this.cars = cars;
        this.model = model;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (cars) {
                DummyCar car = new DummyCar(model);
                cars.add(car);
                /* System.out.printf("%s produced a %s!%nRemaining cars: %d%n",
                        Thread.currentThread().getName(),
                        car,
                        cars.size()); */
                cars.notifyAll();
            }
        }
    }
}
