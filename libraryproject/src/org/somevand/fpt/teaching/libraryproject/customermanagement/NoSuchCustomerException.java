package org.somevand.fpt.teaching.libraryproject.customermanagement;

public class NoSuchCustomerException extends Exception {
    NoSuchCustomerException(int uid) {
        super(String.format("No customer exists with UID %d", uid));
    }
}
