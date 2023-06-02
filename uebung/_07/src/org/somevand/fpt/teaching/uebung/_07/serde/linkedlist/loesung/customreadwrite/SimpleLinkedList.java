package org.somevand.fpt.teaching.uebung._07.serde.linkedlist.loesung.customreadwrite;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E extends Serializable> implements Iterable<E>, Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    private transient Entry head, tail;
    private transient int size;

    public SimpleLinkedList() {

    }

    public void prepend(E data) {
        head = new Entry(null, data, head);
        if (head.next != null) {
            head.next.previous = head;
        }
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void append(E data) {
        tail = new Entry(tail, data, null);
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

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int size = ois.readInt();
        for (int i = 0; i < size; i++) {
            append((E) ois.readObject());
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(size);
        for (E entry : this) {
            oos.writeObject(entry);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleLinkedList<?> that)) return false;

        if (size != that.size) {
            return false;
        }

        Iterator<E> iter;
        Iterator<?> otherIter;
        iter = iterator();
        otherIter = that.iterator();

        while (iter.hasNext() && otherIter.hasNext()) {
            if (!iter.next().equals(otherIter.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = size;
        for (E element : this) {
            result = 31 * result + element.hashCode();
        }
        return result;
    }

    private class Entry {
        E data;
        Entry previous, next;

        Entry(Entry previous, E data, Entry next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }

    // implementation of Iterable<E>, enables the class to be iterated via foreach

    public Iterator<E> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<E> {

        public Iter() {

        }

        private Entry next = head;

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            E data = next.data;
            next = next.next;
            return data;
        }
    }

    public static void main(String[] args) throws Exception {
        long start, end;
        String path = "data";
        SimpleLinkedList<String> l = new SimpleLinkedList<>();
        SimpleLinkedList<String> otherL;
        for (int i = 0; i < 100_000; i++) {
            l.append(String.valueOf(i));
        }
        start = System.nanoTime();
        try (var fos = new FileOutputStream(path);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(l);
        }
        end = System.nanoTime();

        System.out.println(
                "serialisation:\n" +
                        "start:    " + start + "\n" +
                        "end:      " + end + "\n" +
                        "duration: " + (end - start) / 1_000_000 + " ms");
        System.out.println();

        start = System.nanoTime();
        try (var fis = new FileInputStream(path);
             var ois = new ObjectInputStream(fis)) {
            otherL = (SimpleLinkedList<String>) ois.readObject();
        }
        end = System.nanoTime();
        System.out.println(
                "deserialisation:\n" +
                        "start:    " + start + "\n" +
                        "end:      " + end + "\n" +
                        "duration: " + (end - start) / 1_000_000 + " ms");
        System.out.println();

        System.out.println(l.equals(otherL));
    }
}