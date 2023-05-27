package org.somevand.fpt.teaching.libraryproject.securityentities;

import java.util.Set;

public class User {
    private final String uid;
    private final String passkey;
    private final Set<Group> groups;

    public User(String uid, String passkey, Set<Group> groups) {
        this.uid = uid;
        this.passkey = passkey;
        this.groups = groups;
    }

    public String getUid() {
        return uid;
    }

    public String getPasskey() {
        return passkey;
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
