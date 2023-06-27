package org.somevand.fpt.teaching.libraryproject.mediaquery;

import java.util.Collection;
import java.util.Objects;

public record MediaInfo(
        int uid,
        String title,
        Collection<AuthorInfo> authors,
        MediaCategoryInfo categoryInfo,
        Collection<InstanceInfo> instances) {
    public MediaInfo {
        Objects.requireNonNull(title);
        Objects.requireNonNull(authors);
        Objects.requireNonNull(instances);
    }
}
