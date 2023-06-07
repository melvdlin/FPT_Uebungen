package org.somevand.fpt.teaching.libraryproject.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Article extends Media {
    private final DOI doi;

    public Article(int uid, String title, List<Author> authors, LocalDateTime released, DOI doi) {
        super(uid, title, authors, released);
        this.doi = doi;
    }

    public DOI getDoi() {
        return doi;
    }

    @Override
    public String toString() {
        return "Article{" +
                "doi=" + doi +
                "} " + super.toString();
    }
}
