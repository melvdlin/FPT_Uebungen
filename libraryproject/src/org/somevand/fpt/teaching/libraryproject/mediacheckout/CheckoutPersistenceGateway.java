package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.util.Collection;

public interface CheckoutPersistenceGateway {

    void checkout(Collection<PhysicalMediaInstance> instances, Customer customer);

    void checkin(Collection<PhysicalMediaInstance> instances);

    boolean isCheckedOut(PhysicalMediaInstance instance);
}
