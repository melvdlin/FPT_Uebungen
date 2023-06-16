package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import org.somevand.fpt.teaching.libraryproject.entities.Customer;
import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class MediaCheckoutManager {

    private CustomerPersistenceGateway customerPersister;
    private InventoryPersistenceGateway inventoryPersister;
    private CheckoutPersistenceGateway checkoutPersister;

    public MediaCheckoutManager(
            CustomerPersistenceGateway customerPersister,
            InventoryPersistenceGateway inventoryPersister,
            CheckoutPersistenceGateway checkoutPersister
    ) {
        this.customerPersister = Objects.requireNonNull(customerPersister);
        this.inventoryPersister = Objects.requireNonNull(inventoryPersister);
        this.checkoutPersister = Objects.requireNonNull(checkoutPersister);
    }

    public void checkout(
            Collection<InstanceInfo> instancesToCheckout,
            CustomerInfo customerInfo
    ) throws NoSuchCustomerException, NoSuchInstanceException,
            InstanceAlreadyCheckedOutException {
        Customer customer = customerPersister
                .getCustomerByUID(customerInfo.uid())
                .orElseThrow(() -> new NoSuchCustomerException(customerInfo));

        Collection<PhysicalMediaInstance> instances =
                new ArrayList<>(instancesToCheckout.size());

        for (InstanceInfo instanceInfo : instancesToCheckout) {
            PhysicalMediaInstance instance = checkoutPersister
                    .getInstanceByUID(instanceInfo.uid())
                    .orElseThrow(() -> new NoSuchInstanceException(instanceInfo));
            instances.add(instance);
        }

        for (PhysicalMediaInstance instance : instances) {
            if (checkoutPersister.isCheckedOut(instance)) {
                throw new InstanceAlreadyCheckedOutException(instance);
            }
        }

        checkoutPersister.checkout(instances, customer);
    }

    public void checkin(Collection<InstanceInfo> instancesToCheckin)
            throws NoSuchInstanceException, InstanceNotCheckedOutException {

        Collection<PhysicalMediaInstance> instances =
                new ArrayList<>(instancesToCheckin.size());

        for (InstanceInfo instanceInfo : instancesToCheckin) {
            PhysicalMediaInstance instance = checkoutPersister
                    .getInstanceByUID(instanceInfo.uid())
                    .orElseThrow(() -> new NoSuchInstanceException(instanceInfo));
            instances.add(instance);
        }

        for (PhysicalMediaInstance instance : instances) {
            if (!checkoutPersister.isCheckedOut(instance)) {
                throw new InstanceNotCheckedOutException(instance);
            }
        }

        checkoutPersister.checkin(instances);
    }
}
