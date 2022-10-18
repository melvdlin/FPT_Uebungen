package org.somevand.fpt.teaching.uebung._04.exceptions;

import org.somevand.fpt.teaching.uebung._04.data.Ware;

public class OutOfWareException extends GameException {
    private final Ware ware;
    private final int count;
    private final int available;

    public OutOfWareException(Ware ware, int count, int available) {
        this.ware = ware;
        this.count = count;
        this.available = available;
    }

    @Override
    public String getMessage() {
        return
                "Not enough " + ware.name() + " available" +
                " (required: " + count + "; available: " + available + ")";
    }
}
