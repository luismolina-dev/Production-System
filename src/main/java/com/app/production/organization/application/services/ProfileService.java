package com.app.production.organization.application.services;

import com.app.production.organization.application.mappers.ProfileMapper;
import com.app.production.organization.domain.entities.UserProfile;
import com.app.production.organization.domain.exceptions.ProfileNotFoundException;
import com.app.production.organization.domain.exceptions.UserNotFoundException;
import com.app.production.organization.domain.interfaces.ProfilePersistancePort;
import com.app.production.organization.infrastructure.persistence.adapters.ProfilePersistenceAdapter;
import com.app.production.organization.infrastructure.web.dtos.profile.ProfileDto;
import com.app.production.organization.infrastructure.web.dtos.profile.ProfileResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfilePersistancePort profilePersistancePort;

    public ProfileService(ProfileMapper profileMapper, ProfilePersistenceAdapter profilePersistenceAdapter) {
        this.profileMapper = profileMapper;
        this.profilePersistancePort = profilePersistenceAdapter;
    }

    public ProfileResponseDto create(ProfileDto profileDto){
        UserProfile userProfile = profileMapper.toDomain(profileDto);
        UserProfile savedUserProfile = profilePersistancePort.save(userProfile);
        return profileMapper.toResponseDto(savedUserProfile);
    }

    public ProfileResponseDto update(UUID id, ProfileDto profileDto){
        UserProfile profile = profilePersistancePort.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));

        profileMapper.updateDomainFromDto(profileDto, profile);

        UserProfile savedUserProfile = profilePersistancePort.save(profile);
        return profileMapper.toResponseDto(savedUserProfile);
    }

    public ProfileResponseDto getById(UUID id){
        UserProfile userProfile = profilePersistancePort.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));

        return profileMapper.toResponseDto(userProfile);
    }

    public ProfileResponseDto getByUserId(UUID userId){
        UserProfile userProfile = profilePersistancePort.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return profileMapper.toResponseDto(userProfile);
    }

    public void delete(UUID id){
        profilePersistancePort.delete(id);
    }

}
