package org.somevand.fpt.teaching.libraryproject.mediaquery;

import org.somevand.fpt.teaching.libraryproject.entities.Media;
import org.somevand.fpt.teaching.libraryproject.entities.MediaInstance;

import java.util.Collection;

public interface InventoryAccessGateway {
    Collection<MediaInstance> getInstances(Media media);
}
