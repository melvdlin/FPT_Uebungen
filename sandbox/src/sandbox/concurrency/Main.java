package sandbox.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        // int i = 11;
        // Taskmaster taskmaster = new Taskmaster(0xFF);
        // int resSeq, resCon;
        // long startSeq, endSeq, startCon, endCon;
        // String resfmt = "[%s] fibonacci number %d: %d - took %dms%n";
        //
        // startSeq = System.currentTimeMillis();
        // resSeq = fibSequential(i);
        // endSeq = System.currentTimeMillis();
        // System.out.printf(resfmt, "Sequential", i, resSeq, endSeq - startSeq);
        //
        // startCon = System.currentTimeMillis();
        // resCon = fibConcurrent(i, taskmaster);
        // endCon = System.currentTimeMillis();
        // System.out.printf(resfmt, "Concurrent", i, resCon, endCon - startCon);
        System.out.println(fibSequential(11));
        System.out.println(i);
        i = 0;
        System.out.println(fibSequential(25));
        System.out.println(i);
        i = 0;
    }
    private static int i = 0;

    private static int fibSequential(int n) {
        i++;
        if (n < 0) throw new IllegalArgumentException();
        if (n < 2) return 1;
        return fibSequential(n - 2) + fibSequential(n - 1);
    }

    private static int fibConcurrent(int i, Taskmaster taskmaster) {
        if (i < 0) throw new IllegalArgumentException();
        if (i < 2) return 1;
        try {
            PrimitiveFuture<Integer> f1 = taskmaster.submit(() -> fibConcurrent(i - 1, taskmaster));
            PrimitiveFuture<Integer> f2 = taskmaster.submit(() -> fibConcurrent(i - 2, taskmaster));
            return f1.get() + f2.get();
        } catch (InterruptedException e) {
            return -1;
        }
    }
}
