package com.app.production.organization.application.mappers;

import com.app.production.organization.infrastructure.web.dtos.user.UserDto;
import com.app.production.organization.infrastructure.web.dtos.user.UserResponseDto;
import com.app.production.organization.domain.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Domain → DTO
    @Mapping(target = "role", source = "profile.role")
    UserResponseDto toResponseDto(User domain);

    // DTO → Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "profile.role", source = "role")
    User toDomain(UserDto dto);

    // Update existing domain object from DTO (partial update)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "profile.role", source = "role")
    void updateDomainFromDto(UserDto dto, @MappingTarget User domain);
}
