package com.app.production.auth.application.dtos;

import com.app.production.auth.domain.entities.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String username;
    private String documents_name;
    private Role role;
}
