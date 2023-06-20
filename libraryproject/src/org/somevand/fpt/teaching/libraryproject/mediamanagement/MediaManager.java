package org.somevand.fpt.teaching.libraryproject.mediamanagement;

import org.somevand.fpt.teaching.libraryproject.Location;
import org.somevand.fpt.teaching.libraryproject.entities.*;

import java.net.URI;
import java.net.URL;
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

    public int createMedia(MediaCreationInfo mediaInfo)
            throws NoSuchAuthorException, UnknownMediaCategoryException {
        Objects.requireNonNull(mediaInfo);

        String title = Objects.requireNonNull(mediaInfo.title());
        LocalDateTime released = Objects.requireNonNull(mediaInfo.released());

        List<Author> authors =
                new ArrayList<>(Objects.requireNonNull(mediaInfo.authors()).size());
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

        return uid;
    }

    public int createAuthor(AuthorCreationInfo authorInfo) {
        Objects.requireNonNull(authorInfo);

        int uid = inventoryGateway.getNewAuthorUid();
        Author author = new Author(uid, authorInfo.firstName(), authorInfo.lastName());
        inventoryGateway.addAuthor(author);

        return uid;
    }

    public int createDigitalMediaInstance(DigitalInstanceCreationInfo instanceInfo)
            throws NoSuchMediaException, MalformedURLException {
        Objects.requireNonNull(instanceInfo);

        int mediaUid = instanceInfo.mediaUid();
        String host = Objects.requireNonNull(instanceInfo.host());
        URL url;

        try {
            url = URI.create(host).toURL();
        } catch (java.net.MalformedURLException e) {
            throw new MalformedURLException(e.getMessage());
        }

        Media media = inventoryGateway
                .getMediaByUid(mediaUid)
                .orElseThrow(() -> new NoSuchMediaException(mediaUid));
        int uid = inventoryGateway.getNewInstanceUid();

        DigitalMediaInstance instance = new DigitalMediaInstance(uid, media, url);

        inventoryGateway.addDigitalInstance(instance);

        return uid;
    }

    public int createPhysicalMediaInstance(PhysicalInstanceCreationInfo instanceInfo)
            throws NoSuchMediaException {
        Objects.requireNonNull(instanceInfo);

        int mediaUid = instanceInfo.mediaUid();
        Location location = new Location(Objects.requireNonNull(instanceInfo.location()));

        Media media = inventoryGateway
                .getMediaByUid(mediaUid)
                .orElseThrow(() -> new NoSuchMediaException(mediaUid));
        int uid = inventoryGateway.getNewInstanceUid();

        PhysicalMediaInstance instance = new PhysicalMediaInstance(uid, media, location);

        inventoryGateway.addPhysicalInstance(instance);

        return uid;
    }

    public void deleteMedia(MediaDeletionInfo mediaInfo) throws NoSuchMediaException {
        Objects.requireNonNull(mediaInfo);

        Media toDelete = inventoryGateway
                .getMediaByUid(mediaInfo.uid())
                .orElseThrow(() -> new NoSuchMediaException(mediaInfo.uid()));

        inventoryGateway.removeMedia(toDelete);
    }

    public void deleteAuthor(AuthorDeletionInfo authorInfo) throws NoSuchAuthorException {
        Objects.requireNonNull(authorInfo);

        Author toDelete = inventoryGateway
                .getAuthorByUid(authorInfo.uid())
                .orElseThrow(() -> new NoSuchAuthorException(authorInfo.uid()));

        inventoryGateway.removeAuthor(toDelete);
    }

    public void deleteMediaInstance(InstanceDeletionInfo instanceInfo)
            throws NoSuchInstanceException {
        Objects.requireNonNull(instanceInfo);

        MediaInstance toDelete = inventoryGateway
                .getInstanceByUid(instanceInfo.uid())
                .orElseThrow(() -> new NoSuchInstanceException(instanceInfo.uid()));

        inventoryGateway.removeInstance(toDelete);
    }

}
