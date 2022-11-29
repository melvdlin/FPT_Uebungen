package org.somevand.fpt.teaching.uebung._07.serde.linkedlist.loesung.defaultreadwrite;

import java.io.*;

public class SimpleLinkedList<E extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private Entry<E> head, tail;
    private int size;

    public SimpleLinkedList() {

    }

    public void prepend(E data) {
        head = new Entry<>(null, data, head);
        if (head.next != null) {
            head.next.previous = head;
        }
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void append(E data) {
        tail = new Entry<>(tail, data, null);
        if (tail.previous != null) {
            tail.previous.next = tail;
        }
        if (head == null) {
            head = tail;
        }
        size++;
    }

    public E getFirst() {
        return head.data;
    }

    public E getLast() {
        return tail.data;
    }

    public E getAndRemoveFirst() {
        if (head == null) {
            throw new IllegalStateException();
        }
        E item = head.data;
        if (head.next == null) {
            tail = null;
        } else {
            head.next.previous = null;
        }
        head = head.next;
        size--;
        return item;
    }

    public E getAndRemoveLast() {
        if (tail == null) {
            throw new IllegalStateException();
        }
        E item = tail.data;
        if (tail.previous == null) {
            head = null;
        } else {
            tail.previous.next = null;
        }
        tail = tail.previous;
        size--;
        return item;
    }

    public int getSize() {
        return size;
    }

    private static class Entry<E> implements Serializable {
        @Serial
        private static final long serialVersionUID = 0L;

        E data;
        Entry<E> previous, next;

        Entry(Entry<E> previous, E data, Entry<E> next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) throws Exception {
        long start, end;
        String path = "data";
        SimpleLinkedList<String> l = new SimpleLinkedList<>();
        for (int i = 0; i < 100_000; i++) {
            l.append(String.valueOf(i));
        }
        start = System.nanoTime();
        try(var fos = new FileOutputStream(path);
            var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(l);
        }
        end = System.nanoTime();

        System.out.println(
                "serialisation:\n" +
                "start:    " + start + "\n" +
                "end:      " + end + "\n" +
                "duration: " + (end - start) / 1_000_000);
        System.out.println();

        start = System.nanoTime();
        try(var fis = new FileInputStream(path);
            var ois = new ObjectInputStream(fis)) {
            ois.readObject();
        }
        end = System.nanoTime();
        System.out.println(
                "deserialisation:\n" +
                "start:    " + start + "\n" +
                "end:      " + end + "\n" +
                "duration: " + (end - start) / 1_000_000);
        System.out.println();
    }
}