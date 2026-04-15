package com.app.production.organization.infrastructure.web.controllers;

import com.app.production.organization.infrastructure.web.dtos.auth.AuthRequest;
import com.app.production.organization.infrastructure.web.dtos.auth.AuthResponse;
import com.app.production.organization.infrastructure.web.dtos.user.UserDto;
import com.app.production.organization.infrastructure.web.dtos.user.UserResponseDto;
import com.app.production.organization.application.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }
}
