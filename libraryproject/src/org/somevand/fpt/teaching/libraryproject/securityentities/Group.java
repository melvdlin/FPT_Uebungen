package org.somevand.fpt.teaching.libraryproject.securityentities;

import java.util.Objects;

public class Group {
    private final String uid;

    public Group(String uid) {
        this.uid = Objects.requireNonNull(uid);
    }

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "Group{" +
                "uid='" + uid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Group other && uid.equals(other.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }
}
