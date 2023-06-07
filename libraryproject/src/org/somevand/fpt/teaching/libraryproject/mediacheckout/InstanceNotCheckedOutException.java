package org.somevand.fpt.teaching.libraryproject.mediacheckout;

import org.somevand.fpt.teaching.libraryproject.entities.PhysicalMediaInstance;

public class InstanceNotCheckedOutException extends Exception {
    InstanceNotCheckedOutException(PhysicalMediaInstance instance) {
        super(String.format(
                "Physical Media Instance with UID %d is not checked out",
                instance.getUid()
        ));
    }
}
