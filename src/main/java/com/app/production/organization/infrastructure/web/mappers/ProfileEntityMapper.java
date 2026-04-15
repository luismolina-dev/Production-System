package com.app.production.organization.infrastructure.web.mappers;

import com.app.production.organization.domain.entities.UserProfile;
import com.app.production.organization.infrastructure.persistence.entities.IUserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileEntityMapper {

    IUserProfile toJpaEntity(UserProfile domain);

    UserProfile toDomain(IUserProfile entity);
}
