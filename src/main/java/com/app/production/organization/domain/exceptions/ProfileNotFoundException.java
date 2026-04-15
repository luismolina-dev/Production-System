package com.app.production.organization.domain.exceptions;

import com.app.production.common.exceptions.ResourceNotFound;

import java.util.UUID;

public class ProfileNotFoundException extends ResourceNotFound {
    public ProfileNotFoundException(UUID id) {
        super("Profile with id " + id + " not found");
    }
}
