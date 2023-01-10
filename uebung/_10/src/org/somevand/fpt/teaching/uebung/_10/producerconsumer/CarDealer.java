package org.somevand.fpt.teaching.uebung._10.producerconsumer;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class CarDealer implements Runnable {
    private final Collection<CarProducer> producers;
    private final List<Thread> threads;
    private final Fleet fleet;

    public CarDealer(int capacity, Collection<CarProducer> producers) {
        this.fleet = new Fleet(capacity);
        this.producers = new ArrayList<>(producers);
        this.threads = new ArrayList<>(producers.size());
        for (var producer : producers) {
            threads.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Car car = producer.fetch();
                        fleet.add(car);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
    }

    @Override
    public void run() {
        for (var thread : threads) {
            thread.start();
        }
        try {
            wait();
        } catch (InterruptedException e) {
        } finally {
            for (var thread : threads) {
                thread.interrupt();
            }
        }
    }
}
