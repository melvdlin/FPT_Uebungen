package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CheckoutLister {
    private CustomerPersistenceGateway customerGateway;
    private CheckoutPersistenceGateway instanceGateway;

    public CheckoutLister(
            CheckoutPersistenceGateway instanceGateway,
            CustomerPersistenceGateway customerGateway
    ) {
        this.instanceGateway = Objects.requireNonNull(instanceGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    public Collection<PhysicalMediaInstanceInfo> getCheckedOutMediaInstances(
            CustomerInfo checkedOutBy
    ) throws NoSuchCustomerException {

        Objects.requireNonNull(checkedOutBy);
        Customer customer = customerGateway
                .getCustomerByUID(checkedOutBy.UID())
                .orElseThrow(() -> new NoSuchCustomerException(checkedOutBy.UID()));

        Collection<PhysicalMediaInstance> instances = instanceGateway.getCheckedOutMediaInstancesByCustomer(customer);

        return instances.stream().map(physicalMediaInstance -> {
            return new PhysicalMediaInstanceInfo();
        }).collect(Collectors.toList());
    }
}
