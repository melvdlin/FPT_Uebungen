package org.somevand.fpt.teaching.libraryproject.mediaquery;

import org.somevand.fpt.teaching.libraryproject.ISBN;

public record BookInfo(ISBN isbn, int edition) implements MediaCategoryInfo {
    @Override
    public String categoryName() {
        return "Book";
    }
}
