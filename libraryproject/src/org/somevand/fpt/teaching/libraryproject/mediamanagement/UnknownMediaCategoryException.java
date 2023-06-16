package org.somevand.fpt.teaching.libraryproject.mediamanagement;

public class UnknownMediaCategoryException extends Exception {
    public UnknownMediaCategoryException(MediaCategoryInfo category) {
        super(String.format("Unknown media category: %s", category.getClass()));
    }
}
