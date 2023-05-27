package org.somevand.fpt.teaching.libraryproject.authorisation;

import org.somevand.fpt.teaching.libraryproject.securityentities.User;

public interface Authoriser {
    boolean authorise(User user);
}
