package sandbox.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.SynchronousQueue;

public class PrimitiveFuture<T> {
    private final Callable<T> task;
    private final BlockingQueue<T> resultQueue = new ArrayBlockingQueue<>(1);

    public PrimitiveFuture(Callable<T> task) {
        this.task = task;
    }

    public T get() throws InterruptedException {
        return resultQueue.take();
    }
    public void compute() throws InterruptedException {
        try {
            resultQueue.put(task.call());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
