package com.app.production.organization.infrastructure.persistence.repositories;

import com.app.production.organization.infrastructure.persistence.entities.IUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<IUserProfile, UUID> {
}
