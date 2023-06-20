package org.somevand.fpt.teaching.libraryproject;

public record Location(String description) {
    @Override
    public String toString() {
        return description;
    }
}
