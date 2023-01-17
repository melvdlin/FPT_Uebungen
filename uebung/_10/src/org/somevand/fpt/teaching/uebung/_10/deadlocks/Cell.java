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
        try {
            System.out.printf("%s swapping...%n", Thread.currentThread().getName());
            Thread.sleep(10);
        } catch (InterruptedException e) { }
        long temp = getValue();
        long newValue = other.getValue();
        setValue(newValue);
        other.setValue(temp);
    }

    public static void main(String[] args) {
        Cell aCell = new Cell(1);
        Cell anotherCell = new Cell(2);

        Thread aThread = new Thread(() -> aCell.swapValue(anotherCell));
        Thread anotherThread = new Thread(() -> anotherCell.swapValue(aCell));

        aThread.start();
        anotherThread.start();
    }
}
