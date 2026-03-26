package com.app.production.organization.domain.interfaces;

import com.app.production.organization.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface
UserPersistencePort {
    User save(User user);
    Page<User> findAll(Pageable pageable);
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
    boolean existsById(UUID id);
    boolean existsByUsername(String username);
    void deleteById(UUID id);
}
