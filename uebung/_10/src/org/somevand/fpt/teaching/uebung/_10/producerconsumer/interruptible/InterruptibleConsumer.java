package org.somevand.fpt.teaching.uebung._10.producerconsumer.interruptible;

import org.somevand.fpt.teaching.uebung._10.producerconsumer.DummyCar;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class InterruptibleConsumer implements Runnable {

    private final List<DummyCar> cars;
    private final Lock lock;
    private final Condition notEmpty, notFull;

    public InterruptibleConsumer(
            List<DummyCar> cars,
            Lock lock,
            Condition notEmpty,
            Condition notFull) {
        this.cars = cars;
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
                    while (cars.size() == 0) {
                        notEmpty.await();
                    }
                    /* System.out.printf("%s consumed a %s!%nRemaining cars: %d%n",
                            Thread.currentThread().getName(),
                            cars.remove(0),
                            cars.size()); */
                    notFull.signalAll();
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("Consumer %s is exiting...%n", Thread.currentThread().getName());
            }
        }
    }
}
