package org.somevand.fpt.teaching.libraryproject.mediaquery;

import org.somevand.fpt.teaching.libraryproject.entities.*;

import java.util.Collection;
import java.util.Objects;

public class MediaQuerier {
    private InventoryQueryGateway queryGateway;
    private InventoryAccessGateway instanceGateway;

    public MediaQuerier(InventoryQueryGateway queryGateway, InventoryAccessGateway instanceGateway) {
        this.queryGateway = Objects.requireNonNull(queryGateway);
        this.instanceGateway = Objects.requireNonNull(instanceGateway);
    }

    public QueryResult queryMediaUids(Collection<Integer> uids) {
        Objects.requireNonNull(uids);

        Collection<Media> media = queryGateway.queryMediaByUids(uids);


        return new QueryResult(media.stream().map(m -> marshalMedia(m, instanceGateway.getInstances(m))).toList());
    }

    public QueryResult queryMediaTitles(Collection<String> titles, boolean conjunctive) {
        Objects.requireNonNull(titles);

        Collection<Media> media = queryGateway.queryMediaByTitles(titles, conjunctive);


        return new QueryResult(media.stream().map(m -> marshalMedia(m, instanceGateway.getInstances(m))).toList());
    }

    public QueryResult queryAuthorUids(Collection<Integer> uids, boolean conjunctive) {
        Objects.requireNonNull(uids);

        Collection<Author> authors = queryGateway.queryAuthorsByUids(uids);
        Collection<Media> media = queryGateway.queryMediaByAuthors(authors, conjunctive);

        return new QueryResult(media.stream().map(m -> marshalMedia(m, instanceGateway.getInstances(m))).toList());
    }

    public QueryResult queryAuthorNames(
            Collection<AuthorName> names,
            boolean conjunctive) {
        Objects.requireNonNull(names);

        Collection<Author> authors = queryGateway.queryAuthorsByNames(names);
        Collection<Media> media = queryGateway.queryMediaByAuthors(authors, conjunctive);

        return new QueryResult(media.stream().map(m -> marshalMedia(m, instanceGateway.getInstances(m))).toList());
    }

    private static MediaInfo marshalMedia(Media media, Collection<MediaInstance> instances) {
        Collection<AuthorInfo> authors = media.getAuthors().stream().map(author -> new AuthorInfo(
                author.getUid(),
                author.getFirstName(),
                author.getLastName()
        )).toList();
        MediaCategoryInfo categoryInfo = switch (media) {
            case Book book -> new BookInfo(book.getIsbn(), book.getEdition());
            case Article article -> new ArticleInfo(article.getDoi());
            case Video video -> new VideoInfo(video.getDuration());
            default -> throw new IllegalArgumentException("Unknown media type: " + media.getClass());
        };
        Collection<InstanceInfo> instanceInfo = instances.stream().map(instance -> (InstanceInfo) switch (instance) {
            case PhysicalMediaInstance pi -> new PhysicalInstanceInfo(pi.getLocation());
            case DigitalMediaInstance di -> new DigitalInstanceInfo(di.getUrl());
            default -> throw new IllegalArgumentException("Unknown media instance type: " + instance.getClass());
        }).toList();
        return new MediaInfo(media.getUid(), media.getTitle(), authors, categoryInfo, instanceInfo);
    }
}
