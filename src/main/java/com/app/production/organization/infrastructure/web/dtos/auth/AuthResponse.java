package com.app.production.organization.infrastructure.web.dtos.auth;

import com.app.production.organization.infrastructure.web.dtos.user.UserResponseDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private UserResponseDto user;
}
