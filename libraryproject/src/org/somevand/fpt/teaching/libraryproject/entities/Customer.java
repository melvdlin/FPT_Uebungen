package org.somevand.fpt.teaching.libraryproject.entities;

public class Customer {
    private final int uid;

    public Customer(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Customer other && uid == other.uid;
    }

    @Override
    public int hashCode() {
        return uid;
    }
}
