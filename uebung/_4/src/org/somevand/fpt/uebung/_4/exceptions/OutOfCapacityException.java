package org.somevand.fpt.uebung._4.exceptions;

import org.somevand.fpt.uebung._4.Ware;

public class OutOfCapacityException extends GameException {
    private final Ware ware;
    private final int count;
    private final int available;

    public OutOfCapacityException(Ware ware, int count, int available) {
        this.ware = ware;
        this.count = count;
        this.available = available;
    }

    @Override
    public String getMessage() {
        return
                "Not enough capacity to store " + ware.name() + " x" + count +
                " (required: " + ware.size() * count + "; available: " + available + ")";
    }
}
