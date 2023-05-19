package org.somevand.fpt.teaching.libraryproject.data;

import java.time.LocalDateTime;
import java.util.List;

public class Media {

    private final String title;
    private final LocalDateTime released;
    private final List<Author> authors;

    public Media(String title, LocalDateTime released, List<Author> authors) {
        this.title    = title;
        this.released = released;
        this.authors  = authors;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getReleased() {
        return released;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
