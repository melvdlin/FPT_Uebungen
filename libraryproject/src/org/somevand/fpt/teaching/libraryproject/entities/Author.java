package org.somevand.fpt.teaching.libraryproject.entities;

import java.util.Objects;

public class Author {

    private final int uid;
    private final String firstName, lastName;

    public Author(int uid, String firstName, String lastName) {
        this.uid = uid;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
    }

    public int getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Author other && uid == other.uid;
    }

    @Override
    public int hashCode() {
        return uid;
    }
}
