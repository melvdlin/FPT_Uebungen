package org.somevand.fpt.teaching.uebung._10.producerconsumer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Fleet {
    private int fleetCapacity;
    private Deque<Car> fleet = new ArrayDeque<>(fleetCapacity);
    private ReentrantLock fleetLock = new ReentrantLock();
    private Condition notEmpty = fleetLock.newCondition();
    private Condition notFull = fleetLock.newCondition();

    public Fleet(int fleetCapacity) {
        this.fleetCapacity = fleetCapacity;
    }

    public void add(Car car) throws InterruptedException {
        try {
            fleetLock.lockInterruptibly();
            while (fleet.size() >= fleetCapacity) {
                notFull.await();
            }
            fleet.addLast(car);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw e;
        } finally {
            fleetLock.unlock();
        }
    }

    public Car remove() throws InterruptedException {
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
}
