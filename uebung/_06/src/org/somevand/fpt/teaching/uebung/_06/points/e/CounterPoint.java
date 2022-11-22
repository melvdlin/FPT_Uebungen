package org.somevand.fpt.teaching.uebung._06.points.e;

import java.util.concurrent.atomic.AtomicInteger;

class CounterPoint extends Point {

    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public CounterPoint(Point other) {
        super(other.getX(), other.getY());
    }

    public static int numberCreated() {
        return counter.get();
    }
}
