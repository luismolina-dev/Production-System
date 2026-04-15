package com.app.production.organization.domain.exceptions;

import com.app.production.common.exceptions.ResourceNotFound;

import java.util.UUID;

public class UserNotFoundException extends ResourceNotFound {
    
    public UserNotFoundException(UUID id) {
        super("User with id " + id + " not found");
    }

    public UserNotFoundException(String username) {
        super("User with username " + username + " not found");
    }
}
