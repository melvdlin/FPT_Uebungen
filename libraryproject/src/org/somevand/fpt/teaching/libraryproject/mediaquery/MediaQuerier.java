package org.somevand.fpt.teaching.libraryproject.mediaquery;

import org.somevand.fpt.teaching.libraryproject.entities.Author;
import org.somevand.fpt.teaching.libraryproject.entities.Media;

import java.util.Collection;
import java.util.Objects;

public class MediaQuerier {
    private InventoryQueryGateway queryGateway;

    public MediaQuerier(InventoryQueryGateway queryGateway) {
        this.queryGateway = Objects.requireNonNull(queryGateway);
    }

    public QueryResult queryMediaUids(Collection<Integer> uids) {
        Objects.requireNonNull(uids);

        Collection<Media> media = queryGateway.queryMediaByUids(uids);


        return new QueryResult(media.stream().map(MediaQuerier::marshalMedia).toList());
    }

    public QueryResult queryMediaTitles(Collection<String> titles, boolean conjunctive) {
        Objects.requireNonNull(titles);

        Collection<Media> media = queryGateway.queryMediaByTitles(titles, conjunctive);


        return new QueryResult(media.stream().map(MediaQuerier::marshalMedia).toList());
    }

    public QueryResult queryAuthorUids(Collection<Integer> uids, boolean conjunctive) {
        Objects.requireNonNull(uids);

        Collection<Author> authors = queryGateway.queryAuthorsByUids(uids);
        Collection<Media> media = queryGateway.queryMediaByAuthors(authors, conjunctive);

        return new QueryResult(media.stream().map(MediaQuerier::marshalMedia).toList());
    }

    public QueryResult queryAuthorNames(
            Collection<AuthorName> names,
            boolean conjunctive) {
        Objects.requireNonNull(names);

        Collection<Author> authors = queryGateway.queryAuthorsByNames(names);
        Collection<Media> media = queryGateway.queryMediaByAuthors(authors, conjunctive);

        return new QueryResult(media.stream().map(MediaQuerier::marshalMedia).toList());
    }

    private static MediaInfo marshalMedia(Media media) {
        // TODO: implement marshalling of Media to MediaInfo
    }
}
