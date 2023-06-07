package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import java.util.Collection;

public class MediaCheckoutManager {

    public void checkout(
            Collection<InstanceInfo> instances,
            CustomerInfo customer)
            throws NoSuchCustomerException, NoSuchInstanceException, InstanceAlreadyCheckedOutException {

    }

    public void checkin(Collection<InstanceInfo> instances) throws NoSuchInstanceException {

    }
}
