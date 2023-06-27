package org.somevand.fpt.teaching.libraryproject.mediaquery;

import org.somevand.fpt.teaching.libraryproject.DOI;

public record ArticleInfo(DOI doi) implements MediaCategoryInfo {
    @Override
    public String categoryName() {
        return "Article";
    }
}
