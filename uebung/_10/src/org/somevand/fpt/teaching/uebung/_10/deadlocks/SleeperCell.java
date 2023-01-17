package org.somevand.fpt.teaching.uebung._10.deadlocks;

public class SleeperCell {
    private long value;
    public SleeperCell(long value) {
        this.value = value;
    }

    public synchronized long getValue() {
        return value;
    }

    public synchronized void setValue(long value) {
        this.value = value;
    }

    public synchronized void swapValue(SleeperCell other) {
        try {
            System.out.printf("%s swapping...%n", Thread.currentThread().getName());
            Thread.sleep(10);
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        long temp = getValue();
        long newValue = other.getValue();
        setValue(newValue);
        other.setValue(temp);
    }

    public static void main(String[] args) {
        SleeperCell aCell = new SleeperCell(1);
        SleeperCell anotherCell = new SleeperCell(2);

        Thread aThread = new Thread(() -> aCell.swapValue(anotherCell));
        Thread anotherThread = new Thread(() -> anotherCell.swapValue(aCell));

        aThread.start();
        anotherThread.start();
    }
}
