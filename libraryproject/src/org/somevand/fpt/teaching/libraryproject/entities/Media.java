package org.somevand.fpt.teaching.libraryproject.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class Media {

    private final int uid;
    private final String title;
    private final List<Author> authors;
    private final LocalDateTime released;

    public Media(int uid, String title, List<Author> authors, LocalDateTime released) {
        this.uid = uid;
        this.title = Objects.requireNonNull(title);
        this.authors = Objects.requireNonNull(authors);
        this.released = Objects.requireNonNull(released);
    }

    public long getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public LocalDateTime getReleased() {
        return released;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Media other && uid == other.uid;
    }

    @Override
    public int hashCode() {
        return uid;
    }

    @Override
    public String toString() {
        return "Media{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", released=" + released +
                '}';
    }
}
