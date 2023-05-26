package org.somevand.fpt.teaching.libraryproject.entities;

import java.util.Set;

public class User {
    private final String uid;
    private final Set<Group> groups;

    public User(String uid, Set<Group> groups) {
        this.uid = uid;
        this.groups = groups;
    }

    public String getUid() {
        return uid;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User other && uid.equals(other.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", groups=" + groups +
                '}';
    }
}
