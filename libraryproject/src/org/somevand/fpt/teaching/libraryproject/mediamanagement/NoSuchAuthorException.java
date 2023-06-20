package org.somevand.fpt.teaching.libraryproject.mediamanagement;

public class NoSuchAuthorException extends Exception {
    NoSuchAuthorException(int uid) {
        super(String.format("No Author exists with UID %d", uid));
    }

}
