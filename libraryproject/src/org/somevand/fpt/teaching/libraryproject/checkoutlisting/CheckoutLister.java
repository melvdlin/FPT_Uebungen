package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import org.somevand.fpt.teaching.libraryproject.entities.Author;
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

    public Collection<CheckoutInfo> getCheckedOutMediaInstances(
            CustomerInfo checkedOutBy
    ) throws NoSuchCustomerException {

        Objects.requireNonNull(checkedOutBy);
        Customer customer = customerGateway
                .getCustomerByUID(checkedOutBy.UID())
                .orElseThrow(() -> new NoSuchCustomerException(checkedOutBy));

        Collection<PhysicalMediaInstance> instances = instanceGateway.getCheckedOutMediaInstancesByCustomer(customer);

        return instances.stream().map(instance -> new CheckoutInfo(
                new MediaInfo(
                        instance.getMedia().getUid(),
                        instance.getMedia().getTitle(),
                        instance.getMedia()
                                .getAuthors()
                                .stream()
                                .map(Author::toString)
                                .collect(Collectors.toList())
                ),
                instance.getUid(),
                instance.getLocation().toString(),
                instanceGateway.getCheckoutTime(instance).orElseThrow()

        )).collect(Collectors.toList());
    }
}
