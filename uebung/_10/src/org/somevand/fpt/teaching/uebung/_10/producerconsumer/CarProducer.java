package org.somevand.fpt.teaching.uebung._10.producerconsumer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarProducer implements Runnable {
    private int fleetCapacity;
    private Deque<Car> fleet = new ArrayDeque<>(fleetCapacity);
    private ReentrantLock fleetLock = new ReentrantLock();
    private Condition notEmpty = fleetLock.newCondition();
    private Condition notFull = fleetLock.newCondition();

    protected Car build() {
        return new Car();
    };

    public Car fetch() throws InterruptedException {
        Car car;
        try {
            fleetLock.lockInterruptibly();
            while (fleet.isEmpty()) {
                notEmpty.await();
            }
            car = fleet.removeFirst();
        } catch (InterruptedException e) {
            throw e;
        } finally {
            fleetLock.unlock();
        }
        notFull.signalAll();
        return car;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                fleetLock.lockInterruptibly();
                while (fleet.size() >= fleetCapacity) {
                    notFull.await();
                }
                fleet.addLast(new Car());
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                fleetLock.unlock();
            }
        }
    }
}
