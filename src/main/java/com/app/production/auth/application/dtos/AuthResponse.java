package com.app.production.auth.application.dtos;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private UserResponseDto user;
}
