package sandbox.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;

public class Taskmaster {
    private final SynchronousQueue<PrimitiveFuture> taskQueue = new SynchronousQueue<>();
    private final Set<Thread> workerThreads = new HashSet<>();
    private final int maxThreads;

    public Taskmaster(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public <T> PrimitiveFuture<T> submit(Callable<T> task) throws InterruptedException {
        PrimitiveFuture<T> future = new PrimitiveFuture<>(task);
        if (taskQueue.offer(future)) {
            return future;
        }
        synchronized (workerThreads) {
            if (workerThreads.size() < maxThreads) {
                Thread workerThread = new Thread(new Worker());
                workerThreads.add(workerThread);
                workerThread.start();
            }
        }
        taskQueue.put(future);
        return future;
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    taskQueue.take().compute();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}


