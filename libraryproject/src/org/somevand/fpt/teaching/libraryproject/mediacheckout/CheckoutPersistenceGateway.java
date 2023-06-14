package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.util.Collection;
import java.util.Optional;

public interface CheckoutPersistenceGateway {
    Optional<PhysicalMediaInstance> getInstanceByUID(int uid);

    void checkout(Collection<PhysicalMediaInstance> instance, Customer customer);

    void checkin(Collection<PhysicalMediaInstance> instance);

    boolean isCheckedOut(PhysicalMediaInstance instance);
}
