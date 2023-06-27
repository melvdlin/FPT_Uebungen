package org.somevand.fpt.teaching.libraryproject.mediaquery;

import java.time.Duration;

public record VideoInfo(Duration duration) implements MediaCategoryInfo {
    @Override
    public String categoryName() {
        return null;
    }
}
