package com.app.production.organization.application.services;

import com.app.production.organization.application.dtos.AuthRequest;
import com.app.production.organization.application.dtos.AuthResponse;
import com.app.production.organization.application.dtos.UserDto;
import com.app.production.organization.application.dtos.UserResponseDto;
import com.app.production.organization.application.mappers.UserMapper;
import com.app.production.organization.domain.entities.User;
import com.app.production.organization.domain.interfaces.UserPersistencePort;
import com.app.production.organization.infrastructure.security.JwtToken;
import com.app.production.common.exceptions.ResourceAlreadyExists;
import com.app.production.common.exceptions.ResourceNotFound;
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
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        // We need to convert to User (UserDetails) for JwtToken
        com.app.production.organization.infrastructure.persistence.entities.User userDetails = userMapper.toJpaEntity(user);
        String token = jwtToken.generateToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUser(userMapper.toResponseDto(user));
        return response;
    }

    public UserResponseDto register(UserDto userDto) {
        if (userPersistencePort.existsByUsername(userDto.getUsername())) {
            throw new ResourceAlreadyExists("The username " + userDto.getUsername() + " already exists");
        }
        User user = userMapper.toDomain(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userPersistencePort.save(user);
        return userMapper.toResponseDto(savedUser);
    }
}
