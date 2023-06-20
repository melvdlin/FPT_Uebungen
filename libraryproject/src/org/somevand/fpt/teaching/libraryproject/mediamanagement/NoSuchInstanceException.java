package org.somevand.fpt.teaching.libraryproject.mediamanagement;

public class NoSuchInstanceException extends Exception {
    NoSuchInstanceException(int uid) {
        super(String.format("No Media Instance exists with UID %d", uid));
    }
}
