package org.somevand.fpt.teaching.libraryproject.mediamanagement;

public class NoSuchMediaException extends Exception {
    NoSuchMediaException(int uid) {
        super(String.format("No Media exists with UID %d", uid));
    }

}
