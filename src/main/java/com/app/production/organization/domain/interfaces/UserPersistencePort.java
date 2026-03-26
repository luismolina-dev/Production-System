package com.app.production.organization.domain.interfaces;

import com.app.production.organization.domain.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface
UserPersistencePort {
    Users save(Users user);
    Page<Users> findAll(Pageable pageable);
    Optional<Users> findByUsername(String username);
    Optional<Users> findById(UUID id);
    boolean existsById(UUID id);
    boolean existsByUsername(String username);
    void deleteById(UUID id);
}
