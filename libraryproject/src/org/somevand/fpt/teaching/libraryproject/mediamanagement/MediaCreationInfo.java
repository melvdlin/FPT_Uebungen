package org.somevand.fpt.teaching.libraryproject.mediamanagement;

import java.time.LocalDateTime;
import java.util.Collection;

public record MediaCreationInfo(String title, Collection<AuthorInfo> authors,
                                LocalDateTime released, MediaCategoryInfo categoryInfo) {
}
