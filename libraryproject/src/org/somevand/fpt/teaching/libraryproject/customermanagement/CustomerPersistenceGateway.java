package org.somevand.fpt.teaching.libraryproject.customermanagement;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;

import java.util.Optional;

public interface CustomerPersistenceGateway {
    int getNewUID();

    void addCustomer(Customer customer);

    void removeCustomer(Customer customer);

    Optional<Customer> getCustomerByUID(int UID);
}
