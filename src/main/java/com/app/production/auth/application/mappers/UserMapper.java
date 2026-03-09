package com.app.production.auth.application.mappers;

import com.app.production.auth.application.dtos.UserDto;
import com.app.production.auth.application.dtos.UserResponseDto;
import com.app.production.auth.domain.entities.Users;
import com.app.production.auth.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Domain to DTO
    UserResponseDto toResponseDto(Users domain);

    // DTO to Domain
    @Mapping(target = "id", ignore = true)
    Users toDomain(UserDto dto);

    // Domain to JPA Entity
    UserEntity toJpaEntity(Users domain);

    // JPA Entity to Domain
    Users toDomain(UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDomainFromDto(UserDto dto, @MappingTarget Users domain);
}
