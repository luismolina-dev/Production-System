package com.app.production.organization.application.mappers;

import com.app.production.organization.application.dtos.UserDto;
import com.app.production.organization.application.dtos.UserResponseDto;
import com.app.production.organization.domain.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Domain to DTO
    UserResponseDto toResponseDto(User domain);

    // DTO to Domain
    @Mapping(target = "id", ignore = true)
    User toDomain(UserDto dto);

    // Domain to JPA Entity
    // 'authorities' is a computed property in UserDetails — ignore it for mapping
    @Mapping(target = "authorities", ignore = true)
    com.app.production.organization.infrastructure.persistence.entities.User toJpaEntity(User domain);

    // JPA Entity to Domain
    User toDomain(com.app.production.organization.infrastructure.persistence.entities.User entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateDomainFromDto(UserDto dto, @MappingTarget User domain);
}
