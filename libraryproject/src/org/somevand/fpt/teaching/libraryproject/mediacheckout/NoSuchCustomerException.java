package org.somevand.fpt.teaching.libraryproject.mediacheckout;

public class NoSuchCustomerException extends Exception {
    NoSuchCustomerException(CustomerInfo customerInfo) {
        super(String.format("No Customer exists with UID %d", customerInfo.uid()));
    }
}
