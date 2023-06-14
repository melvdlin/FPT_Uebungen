package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;

import java.util.Optional;

public interface CustomerPersistenceGateway {
    Optional<Customer> getCustomerByUID(int UID);
}
