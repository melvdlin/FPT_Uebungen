package org.somevand.fpt.teaching.libraryproject.mediaquery;

import java.util.Objects;

public record AuthorInfo(int uid, String firstName, String lastName) {
    public AuthorInfo {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
    }
}
