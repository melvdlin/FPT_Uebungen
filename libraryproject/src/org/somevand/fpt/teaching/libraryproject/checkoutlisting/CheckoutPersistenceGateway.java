package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.util.Collection;

public interface CheckoutPersistenceGateway {
    Collection<PhysicalMediaInstance> getCheckedOutMediaInstancesByCustomer(Customer customer);
}
