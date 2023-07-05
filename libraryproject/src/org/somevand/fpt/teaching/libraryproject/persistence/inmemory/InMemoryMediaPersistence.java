package org.somevand.fpt.teaching.libraryproject.persistence.inmemory;

import org.somevand.fpt.teaching.libraryproject.entities.*;
import org.somevand.fpt.teaching.libraryproject.mediaquery.AuthorName;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class InMemoryMediaPersistence
        implements
        org.somevand.fpt.teaching.libraryproject.mediamanagement.InventoryPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.mediacheckout.InventoryPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.mediaquery.InventoryAccessGateway,
        org.somevand.fpt.teaching.libraryproject.mediaquery.InventoryQueryGateway {
    private final Collection<Media> media;
    private final Collection<Author> authors;
    private final Collection<MediaInstance> instances;

    private int nextMediaUid, nextAuthorUid, nextInstanceUid;

    public InMemoryMediaPersistence(
            Collection<Media> media,
            Collection<Author> authors,
            Collection<MediaInstance> instances
    ) {
        this.media = new HashSet<>(media);
        this.authors = new HashSet<>(authors);
        this.instances = new HashSet<>(instances);

        nextMediaUid = media.stream().mapToInt(Media::getUid).max().orElse(-1) + 1;
        nextAuthorUid = authors.stream().mapToInt(Author::getUid).max().orElse(-1) + 1;
        nextInstanceUid = instances.stream().mapToInt(MediaInstance::getUid).max().orElse(-1) + 1;
    }

    public InMemoryMediaPersistence() {
        this(List.of(), List.of(), List.of());
    }

    @Override
    public int getNewMediaUid() {
        return nextMediaUid++;
    }

    @Override
    public int getNewInstanceUid() {
        return nextInstanceUid++;
    }

    @Override
    public int getNewAuthorUid() {
        return nextAuthorUid++;
    }

    @Override
    public Optional<Media> getMediaByUid(int uid) {
        return media.stream().filter(media -> media.getUid() == uid).findAny();
    }

    @Override
    public Optional<Author> getAuthorByUid(int uid) {
        return authors.stream().filter(author -> author.getUid() == uid).findAny();
    }

    @Override
    public Optional<MediaInstance> getInstanceByUid(int uid) {
        return instances.stream().filter(instance -> instance.getUid() == uid).findAny();
    }

    @Override
    public Optional<PhysicalMediaInstance> getInstanceByUID(int uid) {
        return getInstanceByUid(uid).orElse(null) instanceof PhysicalMediaInstance instance ? Optional.of(instance) : Optional.empty();
    }

    @Override
    public void addMedia(Media media) {
        this.media.add(media);
    }

    @Override
    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    @Override
    public void addDigitalInstance(DigitalMediaInstance instance) {
        instances.add(instance);
    }

    @Override
    public void addPhysicalInstance(PhysicalMediaInstance instance) {
        instances.add(instance);
    }

    @Override
    public void removeMedia(Media media) {
        this.media.remove(media);
    }

    @Override
    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    @Override
    public void removeInstance(MediaInstance instance) {
        instances.remove(instance);
    }

    @Override
    public Collection<MediaInstance> getInstances(Media media) {
        return instances.stream().filter(instance -> instance.getMedia().equals(media)).toList();
    }

    @Override
    public Collection<Media> queryMediaByUids(Collection<Integer> uids) {
        return media.stream().filter(media -> uids.contains(media.getUid())).toList();
    }

    @Override
    public Collection<Media> queryMediaByTitles(Collection<String> titles, boolean conjunctive) {
        return media.stream().filter(
                media -> conjunctive
                        ? titles.stream().allMatch(title -> media.getTitle().contains(title))
                        : titles.stream().anyMatch(title -> media.getTitle().contains(title))
        ).toList();
    }

    @Override
    public Collection<Media> queryMediaByAuthors(Collection<Author> authors, boolean conjunctive) {
        return media.stream().filter(
                media -> conjunctive
                        ? authors.stream().allMatch(author -> media.getAuthors().contains(author))
                        : authors.stream().anyMatch(author -> media.getAuthors().contains(author))
        ).toList();
    }

    @Override
    public Collection<Author> queryAuthorsByUids(Collection<Integer> uids) {
        return authors.stream().filter(author -> uids.contains(author.getUid())).toList();
    }

    @Override
    public Collection<Author> queryAuthorsByNames(Collection<AuthorName> names) {
        return authors.stream().filter(author -> names.stream().anyMatch(name ->
                (name.firstName().isEmpty() || author.getFirstName().contains(name.firstName().get())) &&
                        (name.lastName().isEmpty() || author.getLastName().contains(name.lastName().get()))
        )).toList();
    }
}
