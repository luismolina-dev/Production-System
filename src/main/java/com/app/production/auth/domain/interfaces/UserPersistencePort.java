package com.app.production.auth.domain.interfaces;

import com.app.production.auth.domain.entities.Users;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistencePort {
    Users save(Users user);
    Optional<Users> findByUsername(String username);
    Optional<Users> findById(UUID id);
    boolean existsById(UUID id);
    boolean existsByUsername(String username);
    void deleteById(UUID id);
}
