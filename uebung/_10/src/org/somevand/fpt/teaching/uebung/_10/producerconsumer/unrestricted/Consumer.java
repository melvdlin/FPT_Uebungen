package org.somevand.fpt.teaching.uebung._10.producerconsumer.unrestricted;

import org.somevand.fpt.teaching.uebung._10.producerconsumer.DummyCar;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

    private final List<DummyCar> cars;

    public Consumer(List<DummyCar> cars) {
        this.cars = cars;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (cars) {
                    while (cars.size() == 0) {
                        cars.wait();
                    }
                    /* System.out.printf("%s consumed a %s!%nRemaining cars: %d%n",
                            Thread.currentThread().getName(),
                            cars.remove(0),
                            cars.size()); */
                    cars.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
