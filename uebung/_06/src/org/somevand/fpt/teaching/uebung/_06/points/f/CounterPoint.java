package org.somevand.fpt.teaching.uebung._06.points.f;

import java.util.concurrent.atomic.AtomicInteger;

class CounterPoint extends Point{

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

    @Override
    public CounterPoint clone() {
        try {
            CounterPoint clone = (CounterPoint) super.clone();
            counter.incrementAndGet();
            return clone;
        } catch (ClassCastException e) {
            throw new AssertionError(e);
        }
    }
}
