package org.somevand.fpt.teaching.libraryproject.mediamanagement;

import org.somevand.fpt.teaching.libraryproject.entities.Author;
import org.somevand.fpt.teaching.libraryproject.entities.Media;

import java.util.Optional;

public interface InventoryPersistenceGateway {
    int getNewMediaUid();

    int getNewInstanceUid();

    int getNewAuthorUid();

    Optional<Author> getAuthorByUid(int uid);

    void addMedia(Media media);
}
