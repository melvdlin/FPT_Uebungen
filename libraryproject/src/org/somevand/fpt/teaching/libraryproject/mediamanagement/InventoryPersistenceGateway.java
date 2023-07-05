package org.somevand.fpt.teaching.libraryproject.mediamanagement;

import org.somevand.fpt.teaching.libraryproject.entities.*;

import java.util.Optional;

public interface InventoryPersistenceGateway {
    int getNewMediaUid();

    int getNewAuthorUid();

    int getNewInstanceUid();
    
    Optional<Media> getMediaByUid(int uid);

    Optional<Author> getAuthorByUid(int uid);

    Optional<MediaInstance> getInstanceByUid(int uid);

    void addMedia(Media media);

    void addAuthor(Author author);

    void addDigitalInstance(DigitalMediaInstance instance);

    void addPhysicalInstance(PhysicalMediaInstance instance);

    void removeMedia(Media media);

    void removeAuthor(Author author);

    void removeInstance(MediaInstance instance);
}
