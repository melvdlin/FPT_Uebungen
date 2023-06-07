package org.somevand.fpt.teaching.libraryproject.mediacheckout;

public class NoSuchInstanceException extends Exception {
    NoSuchInstanceException(InstanceInfo instanceInfo) {
        super(String.format(
                "No Physical Media Instance exists with UID %d",
                instanceInfo.uid()
        ));
    }
}
