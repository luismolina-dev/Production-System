package com.app.production.organization.infrastructure.web.mappers;

import com.app.production.organization.domain.entities.User;
import com.app.production.organization.infrastructure.persistence.entities.IUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "profile.user", ignore = true)
    IUser toJpaEntity(User domain);

    @Mapping(target = "authorities", ignore = true)
    User toDomain(IUser entity);
}
