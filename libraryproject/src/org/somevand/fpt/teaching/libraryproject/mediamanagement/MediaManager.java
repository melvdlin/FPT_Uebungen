package org.somevand.fpt.teaching.libraryproject.mediamanagement;

import org.somevand.fpt.teaching.libraryproject.entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MediaManager {

    InventoryPersistenceGateway inventoryGateway;

    public MediaManager(InventoryPersistenceGateway inventoryGateway) {
        this.inventoryGateway = Objects.requireNonNull(inventoryGateway);
    }

    public void createMedia(MediaCreationInfo mediaInfo) throws NoSuchAuthorException, UnknownMediaCategoryException {
        Objects.requireNonNull(mediaInfo);


        String title = Objects.requireNonNull(mediaInfo.title());
        LocalDateTime released = Objects.requireNonNull(mediaInfo.released());

        List<Author> authors = new ArrayList<>(Objects.requireNonNull(mediaInfo.authors()).size());
        for (AuthorInfo(int uid) : mediaInfo.authors()) {
            Optional<Author> author = inventoryGateway.getAuthorByUid(uid);
            authors.add(author.orElseThrow(() -> new NoSuchAuthorException(uid)));
        }

        int uid = inventoryGateway.getNewMediaUid();

        Media media = switch (mediaInfo.categoryInfo()) {
            case BookCategoryInfo bookInfo -> new Book(
                    uid, title, authors, released,
                    Objects.requireNonNull(bookInfo.isbn()),
                    bookInfo.edition());
            case ArticleCategoryInfo articleInfo -> new Article(
                    uid, title, authors, released,
                    Objects.requireNonNull(articleInfo.doi())
            );
            case VideoCategoryInfo videoInfo -> new Video(
                    uid, title, authors, released,
                    Objects.requireNonNull(videoInfo.length())
            );
            default -> throw new UnknownMediaCategoryException(mediaInfo.categoryInfo());
        };

        inventoryGateway.addMedia(media);
    }

    public void createDigitalMediaInstance(DigitalInstanceCreationInfo instanceInfo) {

    }

    public void createPhysicalMediaInstance(PhysicalInstanceCreationInfo instanceInfo) {

    }

    public void deleteMedia(MediaDeletionInfo mediaInfo) {

    }

    public void deleteMediaInstance(InstanceDeletionInfo instanceInfo) {

    }
}
