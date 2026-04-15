package com.app.production.organization.application.services;

import com.app.production.organization.infrastructure.web.dtos.auth.AuthRequest;
import com.app.production.organization.infrastructure.web.dtos.auth.AuthResponse;
import com.app.production.organization.infrastructure.web.dtos.user.UserDto;
import com.app.production.organization.infrastructure.web.dtos.user.UserResponseDto;
import com.app.production.organization.application.mappers.UserMapper;
import com.app.production.organization.domain.entities.User;
import com.app.production.organization.domain.interfaces.UserPersistencePort;
import com.app.production.organization.infrastructure.security.JwtToken;
import com.app.production.organization.domain.exceptions.UserAlreadyExistsException;
import com.app.production.organization.domain.exceptions.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserPersistencePort userPersistencePort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtToken jwtToken;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserPersistencePort userPersistencePort, 
                       UserMapper userMapper, 
                       PasswordEncoder passwordEncoder, 
                       JwtToken jwtToken, 
                       AuthenticationManager authenticationManager) {
        this.userPersistencePort = userPersistencePort;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtToken = jwtToken;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userPersistencePort.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        String token = jwtToken.generateToken(user);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUser(userMapper.toResponseDto(user));
        return response;
    }

    public UserResponseDto register(UserDto userDto) {
        if (userPersistencePort.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistsException(userDto.getUsername());
        }
        User user = userMapper.toDomain(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userPersistencePort.save(user);
        return userMapper.toResponseDto(savedUser);
    }
}
