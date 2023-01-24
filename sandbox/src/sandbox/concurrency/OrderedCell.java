package sandbox.concurrency;

public class OrderedCell {
    private static volatile Object o = new Object();
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
        }
        synchronized (OrderedCell.class) {

        }
    }

    public synchronized void unsafeSwapValue(OrderedCell other) {
        long temp = this.getValue();
        long newValue = other.getValue();
        this.setValue(newValue);
        other.setValue(temp);
    }
}
