package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import org.somevand.fpt.teaching.libraryproject.entities.Author;
import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CheckoutLister {
    private CustomerPersistenceGateway customerPersister;
    private CheckoutPersistenceGateway instancePersister;

    public CheckoutLister(
            CheckoutPersistenceGateway instancePersister,
            CustomerPersistenceGateway customerPersister
    ) {
        this.instancePersister = Objects.requireNonNull(instancePersister);
        this.customerPersister = Objects.requireNonNull(customerPersister);
    }

    public Collection<CheckoutInfo> getCheckedOutMediaInstances(
            CustomerInfo checkedOutBy
    ) throws NoSuchCustomerException {

        Objects.requireNonNull(checkedOutBy);
        Customer customer = customerPersister
                .getCustomerByUID(checkedOutBy.UID())
                .orElseThrow(() -> new NoSuchCustomerException(checkedOutBy));

        Collection<PhysicalMediaInstance> instances = instancePersister.getCheckedOutMediaInstancesByCustomer(customer);

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
                instancePersister.getCheckoutTime(instance).orElseThrow()

        )).collect(Collectors.toList());
    }
}
