package org.somevand.fpt.uebung._4.exceptions;

import org.somevand.fpt.uebung._4.Market;

public class OutOfFuelException extends GameException {
    private final Market destination;
    private final int required;
    private final int available;

    public OutOfFuelException(Market destination, int required, int available) {
        this.destination = destination;
        this.required = required;
        this.available = available;
    }

    @Override
    public String getMessage() {
        return
                "Not enough fuel to reach " + destination.getName() +
                " (required: " + required + "; available: " + available + ")";
    }
}
