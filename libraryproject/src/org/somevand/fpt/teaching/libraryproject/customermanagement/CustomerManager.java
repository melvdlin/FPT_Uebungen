package org.somevand.fpt.teaching.libraryproject.customermanagement;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;

import java.util.Objects;

public class CustomerManager {
    private CustomerPersistenceGateway persistenceGateway;

    public CustomerManager(CustomerPersistenceGateway persistenceGateway) {
        this.persistenceGateway = Objects.requireNonNull(persistenceGateway);
    }

    public void createCustomerAccount(CustomerCreationInfo creationInfo) {
        int newUID = persistenceGateway.getNewUID();
        Customer newCustomer = new Customer(newUID);
        persistenceGateway.addCustomer(newCustomer);
    }

    public void deleteCustomerAccount(CustomerDeletionInfo deletionInfo) throws NoSuchCustomerException {
        Objects.requireNonNull(deletionInfo);

        Customer toRemove = persistenceGateway
                .getCustomerByUID(deletionInfo.UID())
                .orElseThrow(() -> new NoSuchCustomerException(deletionInfo.UID()));
        persistenceGateway.removeCustomer(toRemove);
    }
}
