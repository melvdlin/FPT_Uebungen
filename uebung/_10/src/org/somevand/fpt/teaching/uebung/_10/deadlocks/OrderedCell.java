package org.somevand.fpt.teaching.uebung._10.deadlocks;

public class OrderedCell {
    private long value;
    public OrderedCell(long value) {
        this.value = value;
    }

    public synchronized long getValue() {
        return value;
    }

    public synchronized void setValue(long value) {
        this.value = value;
    }

    public void swapValue(OrderedCell other) {
        try {
            System.out.printf("%s swapping...%n", Thread.currentThread().getName());
            Thread.sleep(10);
        } catch (InterruptedException e) { }
        if (System.identityHashCode(this) < System.identityHashCode(other)) {
            this.unsafeSwapValue(other);
        } else if (System.identityHashCode(this) > System.identityHashCode(other)) {
            other.unsafeSwapValue(this);
        } else {
            synchronized (OrderedCell.class) {
                this.unsafeSwapValue(other);
            }
        }
    }

    private void unsafeSwapValue(OrderedCell other) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) { }
        long temp = this.getValue();
        long newValue = other.getValue();
        this.setValue(newValue);
        other.setValue(temp);
    }

    public static void main(String[] args) {
        OrderedCell aCell = new OrderedCell(1);
        OrderedCell anotherCell = new OrderedCell(2);

        Thread aThread = new Thread(() -> aCell.swapValue(anotherCell));
        Thread anotherThread = new Thread(() -> anotherCell.swapValue(aCell));

        aThread.start();
        anotherThread.start();
    }
}
