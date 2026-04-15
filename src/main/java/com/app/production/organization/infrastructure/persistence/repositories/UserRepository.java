package com.app.production.organization.infrastructure.persistence.repositories;

import com.app.production.organization.infrastructure.persistence.entities.IUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<IUser, UUID> {
    Optional<IUser> findByUsername(String username);
    Boolean existsByUsername(String username);
}
