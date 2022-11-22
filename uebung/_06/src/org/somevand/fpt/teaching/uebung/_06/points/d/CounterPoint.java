package org.somevand.fpt.teaching.uebung._06.points.d;

import java.util.concurrent.atomic.AtomicInteger;

class CounterPoint extends Point {

    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public static int numberCreated() {
        return counter.get();
    }
}
