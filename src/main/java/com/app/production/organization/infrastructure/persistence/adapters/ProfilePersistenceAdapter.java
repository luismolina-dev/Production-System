package com.app.production.organization.infrastructure.persistence.adapters;

import com.app.production.organization.domain.entities.UserProfile;
import com.app.production.organization.domain.interfaces.ProfilePersistancePort;
import com.app.production.organization.infrastructure.persistence.entities.IUserProfile;
import com.app.production.organization.infrastructure.persistence.repositories.ProfileRepository;
import com.app.production.organization.infrastructure.web.mappers.ProfileEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProfilePersistenceAdapter implements ProfilePersistancePort {

    private final ProfileRepository profileRepository;
    private final ProfileEntityMapper profileEntityMapper;
    public ProfilePersistenceAdapter(ProfileRepository profileRepository, ProfileEntityMapper profileEntityMapper) {
        this.profileRepository = profileRepository;
        this.profileEntityMapper = profileEntityMapper;
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        IUserProfile entity = profileEntityMapper.toJpaEntity(userProfile);
        IUserProfile savedEntity = profileRepository.save(entity);
        return profileEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<UserProfile> findById(UUID id) {
        return profileRepository.findById(id)
                .map(profileEntityMapper::toDomain);
    }

    @Override
    public Optional<UserProfile> findByUserId(UUID userId) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {
        profileRepository.deleteById(id);
    }
}
