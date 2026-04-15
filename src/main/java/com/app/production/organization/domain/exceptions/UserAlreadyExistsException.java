package com.app.production.organization.domain.exceptions;

import com.app.production.common.exceptions.ResourceAlreadyExists;

public class UserAlreadyExistsException extends ResourceAlreadyExists {

    public UserAlreadyExistsException(String username) {
        super("The username " + username + " already exists");
    }
}
