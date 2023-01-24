package sandbox.databases;

import sandbox.concurrency.OrderedCell;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        OrderedCell cell1 = new OrderedCell(1);
        OrderedCell cell2 = new OrderedCell(2);

        Thread t1 = new Thread(() -> cell1.swapValue(cell2));
        Thread t2 = new Thread(() -> cell2.swapValue(cell1));
    }
}
