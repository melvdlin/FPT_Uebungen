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
        long temp = this.getValue();
        long newValue = other.getValue();
        this.setValue(newValue);
        other.setValue(temp);
    }
}
