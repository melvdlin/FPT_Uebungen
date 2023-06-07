package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface CheckoutPersistenceGateway {
    Collection<PhysicalMediaInstance> getCheckedOutMediaInstancesByCustomer(Customer customer);

    Optional<LocalDateTime> getCheckoutTime(PhysicalMediaInstance instance);
}
