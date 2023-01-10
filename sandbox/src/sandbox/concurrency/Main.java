package sandbox.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String outFormat = "fib sum %d-%d: %d; duration: %dms";
        int from = 1;
        int to = 46;
        int fibSumSequential, fibSumConcurrent;
        long durationSequential, durationConcurrent;

        long start = System.currentTimeMillis();
        fibSumSequential = fibSumSequential(from, to);
        durationSequential = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        fibSumConcurrent = fibSumConcurrent(from, to);
        durationConcurrent = System.currentTimeMillis() - start;

        System.out.println("Sequential " + outFormat.formatted(from, to, fibSumSequential, durationSequential));
        System.out.println("Concurrent " + outFormat.formatted(from, to, fibSumConcurrent, durationConcurrent));
    }

    private static int fibSumSequential(int from, int to) {
        if (to < from || from < 0) throw new IllegalArgumentException();
        int sum = 0;
        for (; from < to; from++) {
            sum += fib(from);
        }
        return sum;
    }

    private static int fibSumConcurrent(int from, int to) throws ExecutionException, InterruptedException {
        if (to < from || from < 0) throw new IllegalArgumentException();

        int sum = 0;
        try (var exec = Executors.newCachedThreadPool()) {
            List<Future<Integer>> futures = new ArrayList<>(to - from);
            for (; from < to; from ++) {
                int finalFrom = from;
                futures.add(exec.submit(() -> fib(finalFrom)));
            }
            for (var future : futures) {
                sum += future.get();
            }
        }
        return sum;
    }

    private static int fib(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n < 2) return n;
        return fib(n - 2) + fib (n - 1);
    }
}
