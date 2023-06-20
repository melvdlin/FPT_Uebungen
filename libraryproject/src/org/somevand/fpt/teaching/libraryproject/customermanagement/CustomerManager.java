package org.somevand.fpt.teaching.libraryproject.customermanagement;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;

import java.util.Objects;

public class CustomerManager {
    private CustomerPersistenceGateway customerPersister;

    public CustomerManager(CustomerPersistenceGateway customerPersister) {
        this.customerPersister = Objects.requireNonNull(customerPersister);
    }

    public int createCustomerAccount(CustomerCreationInfo creationInfo) {
        int newUID = customerPersister.getNewUID();
        Customer newCustomer = new Customer(newUID);
        customerPersister.addCustomer(newCustomer);

        return newUID;
    }

    public void deleteCustomerAccount(CustomerDeletionInfo deletionInfo)
            throws NoSuchCustomerException {
        Objects.requireNonNull(deletionInfo);

        Customer toRemove = customerPersister
                .getCustomerByUID(deletionInfo.UID())
                .orElseThrow(() -> new NoSuchCustomerException(deletionInfo.UID()));
        customerPersister.removeCustomer(toRemove);
    }
}
