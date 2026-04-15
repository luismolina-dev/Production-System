package com.app.production.organization.infrastructure.web.dtos.user;

import com.app.production.organization.domain.entities.Role;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private Role role;
}
