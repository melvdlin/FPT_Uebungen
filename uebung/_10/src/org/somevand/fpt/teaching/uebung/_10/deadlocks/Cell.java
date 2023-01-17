package org.somevand.fpt.teaching.uebung._10.deadlocks;

public class Cell {
    private long value;
    public Cell(long value) {
        this.value = value;
    }

    public synchronized long getValue() {
        return value;
    }

    public synchronized void setValue(long value) {
        this.value = value;
    }

    public synchronized void swapValue(Cell other) {
        long temp = getValue();
        long newValue = other.getValue();
        setValue(newValue);
        other.setValue(temp);
    }
}
