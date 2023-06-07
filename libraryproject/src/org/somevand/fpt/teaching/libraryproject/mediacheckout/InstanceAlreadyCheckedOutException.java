package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

public class InstanceAlreadyCheckedOutException extends Exception {
    InstanceAlreadyCheckedOutException(PhysicalMediaInstance instance) {
        super(String.format(
                "Physical Media Instance with UID %d is already checked out",
                instance.getUid()
        ));
    }
}
