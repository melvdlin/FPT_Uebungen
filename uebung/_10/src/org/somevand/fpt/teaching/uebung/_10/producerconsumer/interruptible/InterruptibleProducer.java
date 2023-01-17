package org.somevand.fpt.teaching.uebung._10.producerconsumer.interruptible;

import org.somevand.fpt.teaching.uebung._10.producerconsumer.DummyCar;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class InterruptibleProducer implements Runnable {

    private final List<DummyCar> cars;
    private final String model;
    private final int maxCarCount;
    private final Lock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    public InterruptibleProducer(
            List<DummyCar> cars,
            String model,
            int maxCarCount,
            Lock lock,
            Condition notEmpty,
            Condition notFull) {
        this.cars = cars;
        this.model = model;
        this.maxCarCount = maxCarCount;
        this.lock = lock;
        this.notEmpty = notEmpty;
        this.notFull = notFull;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                lock.lockInterruptibly();
                try {
                    while (cars.size() >= maxCarCount) {
                        notFull.await();
                    }
                    DummyCar car = new DummyCar(model);
                    cars.add(car);
                    /* System.out.printf("%s produced a %s!%nRemaining cars: %d%n",
                            Thread.currentThread().getName(),
                            car,
                            cars.size()); */
                    notEmpty.signalAll();
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("Producer %s is exiting...%n", Thread.currentThread().getName());
            }
        }
    }
}
