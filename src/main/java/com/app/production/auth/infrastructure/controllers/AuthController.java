package com.app.production.auth.infrastructure.controllers;

import com.app.production.auth.application.dtos.AuthRequest;
import com.app.production.auth.application.dtos.AuthResponse;
import com.app.production.auth.application.dtos.UserDto;
import com.app.production.auth.application.dtos.UserResponseDto;
import com.app.production.auth.application.services.AuthService;
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
