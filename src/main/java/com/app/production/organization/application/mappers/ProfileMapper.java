package com.app.production.organization.application.mappers;

import com.app.production.organization.domain.entities.UserProfile;
import com.app.production.organization.infrastructure.web.dtos.profile.ProfileDto;
import com.app.production.organization.infrastructure.web.dtos.profile.ProfileResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileResponseDto toResponseDto(UserProfile domain);

    @Mapping(target="id", ignore = true)
    UserProfile toDomain(ProfileDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="id", ignore = true)
    void updateDomainFromDto(ProfileDto dto, @MappingTarget UserProfile domain);
}
