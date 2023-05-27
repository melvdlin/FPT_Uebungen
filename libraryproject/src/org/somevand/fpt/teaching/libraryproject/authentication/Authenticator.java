package org.somevand.fpt.teaching.libraryproject.authentication;

import org.somevand.fpt.teaching.libraryproject.securityentities.User;

public interface Authenticator {
    User authenticate(Credentials credentials);
}
