package org.somevand.fpt.teaching.libraryproject.mediaquery;

import java.util.Objects;
import java.util.Optional;

public record AuthorName(Optional<String> firstName, Optional<String> lastName) {
    public AuthorName {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
    }
}
