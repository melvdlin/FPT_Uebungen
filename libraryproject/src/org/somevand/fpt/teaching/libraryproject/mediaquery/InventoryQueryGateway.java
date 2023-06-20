package org.somevand.fpt.teaching.libraryproject.mediaquery;

import org.somevand.fpt.teaching.libraryproject.entities.Author;
import org.somevand.fpt.teaching.libraryproject.entities.Media;

import java.util.Collection;

public interface InventoryQueryGateway {
    Collection<Media> queryMediaByUids(Collection<Integer> uids);

    Collection<Media> queryMediaByTitles(Collection<String> titles, boolean conjunctive);

    Collection<Media> queryMediaByAuthors(
            Collection<Author> authors,
            boolean conjunctive);

    Collection<Author> queryAuthorsByUids(Collection<Integer> uids);

    Collection<Author> queryAuthorsByNames(Collection<AuthorName> names);
}
