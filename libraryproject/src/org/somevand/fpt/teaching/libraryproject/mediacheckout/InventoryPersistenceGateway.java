package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.util.Optional;

public interface InventoryPersistenceGateway {
    Optional<PhysicalMediaInstance> getInstanceByUID(int uid);
}
