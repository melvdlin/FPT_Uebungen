package org.somevand.fpt.teaching.uebung.old._04.exceptions;

import org.somevand.fpt.teaching.uebung.old._04.data.Ware;

public class CannotAffordException extends GameException {
    private final Ware ware;
    private final int count;
    private final int priceMultiplier;
    private final int available;

    public CannotAffordException(Ware ware, int count, int priceMultiplier, int available) {
        this.ware = ware;
        this.count = count;
        this.priceMultiplier = priceMultiplier;
        this.available = available;
    }

    @Override
    public String getMessage() {
        return
                "Cannot afford " + ware.name() + " x" + count +
                " (costs: " + ware.basePrice() * count * priceMultiplier + "; money available: " + available;
    }
}
