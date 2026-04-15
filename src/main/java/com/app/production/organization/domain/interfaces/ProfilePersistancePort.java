package com.app.production.organization.domain.interfaces;

import com.app.production.organization.domain.entities.UserProfile;

import java.util.Optional;
import java.util.UUID;

public interface ProfilePersistancePort {
    UserProfile save(UserProfile userProfile);
    Optional<UserProfile> findById(UUID id);
    Optional<UserProfile> findByUserId(UUID userId);
    void delete(UUID userProfile);
}
