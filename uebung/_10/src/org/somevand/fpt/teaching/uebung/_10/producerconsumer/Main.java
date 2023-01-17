package org.somevand.fpt.teaching.uebung._10.producerconsumer;

import org.somevand.fpt.teaching.uebung._10.producerconsumer.interruptible.InterruptibleConsumer;
import org.somevand.fpt.teaching.uebung._10.producerconsumer.interruptible.InterruptibleProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<DummyCar> cars = new ArrayList<>();
        int maxCarCount = 30;
        Lock lock = new ReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition notFull = lock.newCondition();

        int producerCount = 10;
        int consumerCount = 5;
        List<Thread> producers = new ArrayList<>(producerCount);
        List<Thread> consumers = new ArrayList<>(consumerCount);

        for (int i = 0; i < producerCount; i++) {
            producers.add(new Thread(new InterruptibleProducer(
                    cars,
                    "model"+ i,
                    maxCarCount,
                    lock,
                    notEmpty,
                    notFull
            )));
        }
        for (int i = 0; i < consumerCount; i++) {
            consumers.add(new Thread(new InterruptibleConsumer(
                    cars,
                    lock,
                    notEmpty,
                    notFull
            )));
        }

        producers.forEach(Thread::start);
        consumers.forEach(Thread::start);

        Random random = new Random();
        double killChance = 0.3;

        while (!producers.isEmpty() || !consumers.isEmpty()) {
            Thread.sleep(500);
            if (!producers.isEmpty() && random.nextDouble() <= killChance) {
                producers.remove(random.nextInt(0, producers.size())).interrupt();
            }
            Thread.sleep(500);
            if (!consumers.isEmpty() && random.nextDouble() <= killChance) {
                consumers.remove(random.nextInt(0, consumers.size())).interrupt();
            }
        }
    }
}

