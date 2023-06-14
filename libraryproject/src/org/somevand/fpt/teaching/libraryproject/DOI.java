package org.somevand.fpt.teaching.libraryproject;

import java.util.Objects;

public record DOI(String registrantCode, String suffix) {
    public DOI {
        Objects.requireNonNull(registrantCode);
        Objects.requireNonNull(suffix);
    }

    @Override
    public String toString() {
        return String.format("doi:%s/%s", registrantCode, suffix);
    }
}
