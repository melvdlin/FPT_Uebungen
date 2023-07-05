package org.somevand.fpt.teaching.libraryproject.persistence.inmemory;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class InMemoryCustomerPersistence implements
        org.somevand.fpt.teaching.libraryproject.customermanagement.CustomerPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.checkoutlisting.CustomerPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.mediacheckout.CustomerPersistenceGateway {
    private final Collection<Customer> customers;
    private int nextUid;

    public InMemoryCustomerPersistence(Collection<Customer> customers) {
        this.customers = new HashSet<>(customers);
        nextUid = customers.stream().mapToInt(Customer::getUid).max().orElse(-1) + 1;
    }

    public InMemoryCustomerPersistence() {
        this(List.of());
    }


    @Override
    public int getNewUID() {
        return nextUid++;
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public Optional<Customer> getCustomerByUID(int UID) {
        return customers.stream().filter(customer -> customer.getUid() == UID).findAny();
    }
}
