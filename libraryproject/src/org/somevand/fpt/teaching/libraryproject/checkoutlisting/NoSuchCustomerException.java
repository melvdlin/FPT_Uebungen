package org.somevand.fpt.teaching.libraryproject.checkoutlisting;

import java.util.Objects;

public class NoSuchCustomerException extends Exception {
    NoSuchCustomerException(CustomerInfo customerInfo) {
        super(String.format("No customer exists with UID %d", Objects.requireNonNull(customerInfo).UID()));
    }
}
