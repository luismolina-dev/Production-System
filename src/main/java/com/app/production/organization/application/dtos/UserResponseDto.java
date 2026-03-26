package com.app.production.organization.application.dtos;

import com.app.production.organization.domain.entities.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String username;
    private Role role;
}
