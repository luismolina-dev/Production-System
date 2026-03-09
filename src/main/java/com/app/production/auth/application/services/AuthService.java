package com.app.production.auth.application.services;

import com.app.production.auth.application.dtos.AuthRequest;
import com.app.production.auth.application.dtos.AuthResponse;
import com.app.production.auth.application.dtos.UserDto;
import com.app.production.auth.application.dtos.UserResponseDto;
import com.app.production.auth.application.mappers.UserMapper;
import com.app.production.auth.domain.entities.Users;
import com.app.production.auth.domain.interfaces.UserPersistencePort;
import com.app.production.auth.infrastructure.security.JwtToken;
import com.app.production.auth.infrastructure.persistence.entities.UserEntity;
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

        Users user = userPersistencePort.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        // We need to convert to UserEntity (UserDetails) for JwtToken
        UserEntity userDetails = userMapper.toJpaEntity(user);
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
        Users user = userMapper.toDomain(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = userPersistencePort.save(user);
        return userMapper.toResponseDto(savedUser);
    }
}
