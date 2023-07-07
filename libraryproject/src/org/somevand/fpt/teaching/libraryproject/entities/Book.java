package org.somevand.fpt.teaching.libraryproject.entities;

import org.somevand.fpt.teaching.libraryproject.ISBN;

import java.time.LocalDateTime;
import java.util.List;

public class Book extends Media {
    private final ISBN isbn;
    private final int edition;

    public Book(
            int uid,
            String title,
            List<Author> authors,
            LocalDateTime released,
            ISBN isbn,
            int edition) {
        super(uid, title, authors, released);
        this.isbn = isbn;
        this.edition = edition;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public int getEdition() {
        return edition;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", edition=" + edition +
                "} " + super.toString();
    }
}
