package org.somevand.fpt.teaching.libraryproject.persistence.inmemory;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.time.LocalDateTime;
import java.util.*;

public class InMemoryCheckoutPersistence implements
        org.somevand.fpt.teaching.libraryproject.mediacheckout.CheckoutPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.checkoutlisting.CheckoutPersistenceGateway {
    private final Map<PhysicalMediaInstance, CheckoutRecord> checkedOutInstances = new HashMap<>();

    @Override
    public Collection<PhysicalMediaInstance> getCheckedOutMediaInstancesByCustomer(Customer customer) {
        return checkedOutInstances.entrySet()
                                  .stream()
                                  .filter(entry -> entry.getValue().customer().equals(customer))
                                  .map(Map.Entry::getKey)
                                  .toList();
    }

    @Override
    public Optional<LocalDateTime> getCheckoutTime(PhysicalMediaInstance instance) {
        return checkedOutInstances.entrySet()
                                  .stream()
                                  .filter(entry -> entry.getKey().equals(instance))
                                  .map(Map.Entry::getValue)
                                  .map(CheckoutRecord::checkoutTime)
                                  .findAny();
    }

    @Override
    public void checkout(Collection<PhysicalMediaInstance> instances, Customer customer) {
        LocalDateTime now = LocalDateTime.now();
        for (var instance : instances) {
            checkedOutInstances.put(instance, new CheckoutRecord(customer, now));
        }
    }

    @Override
    public void checkin(Collection<PhysicalMediaInstance> instances) {
        instances.forEach(checkedOutInstances::remove);
    }

    @Override
    public boolean isCheckedOut(PhysicalMediaInstance instance) {
        return checkedOutInstances.containsKey(instance);
    }

    private record CheckoutRecord(Customer customer, LocalDateTime checkoutTime) {
        private CheckoutRecord {
            Objects.requireNonNull(customer);
            Objects.requireNonNull(checkoutTime);
        }
    }
}
