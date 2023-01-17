package sandbox;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread t = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.printf("%s exiting..." + Thread.currentThread().getName());
            } finally {
                lock.unlock();
                System.out.println("Unlocked!");
            }
        });

        lock.lock();
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }
}