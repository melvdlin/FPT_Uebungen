package org.somevand.fpt.teachung.uebung._04.points.c;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterPoint extends Point {

    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public static int numberCreated() {
        return counter.get();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CounterPoint other = (CounterPoint) o;
        return super.equals(o);
    }
}
