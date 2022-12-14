package org.somevand.fpt.teaching.uebung.old._04.exceptions;

public class OutOfFuelException extends GameException {
    private final int required;
    private final int available;

    public OutOfFuelException(int required, int available) {
        this.required = required;
        this.available = available;
    }

    @Override
    public String getMessage() {
        return
                "Not enough fuel to travel the required distance" +
                " (required: " + required + "; available: " + available + ")";
    }
}
