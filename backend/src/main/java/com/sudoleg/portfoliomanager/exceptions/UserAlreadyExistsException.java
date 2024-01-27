package com.sudoleg.portfoliomanager.exceptions;

import jakarta.persistence.PersistenceException;

/**
 * Is thrown when the client tries to create a user with a username or email that is already taken.
 */
public class UserAlreadyExistsException extends PersistenceException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
