package com.app.production.auth.application.services;

import com.app.production.auth.application.dtos.UserDto;
import com.app.production.auth.application.dtos.UserResponseDto;
import com.app.production.auth.application.mappers.UserMapper;
import com.app.production.auth.domain.entities.Users;
import com.app.production.auth.domain.interfaces.UserPersistencePort;
import com.app.production.common.exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserPersistencePort userPersistencePort;
    private final UserMapper userMapper;

    public UserService(UserPersistencePort userPersistencePort, UserMapper userMapper) {
        this.userPersistencePort = userPersistencePort;
        this.userMapper = userMapper;
    }

    public UserResponseDto getById(UUID id) {
        Users user = userPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User with id " + id + " not found"));

        return userMapper.toResponseDto(user);
    }

    public UserResponseDto getByUsername(String username) {
        Users user = userPersistencePort.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User with username " + username + " not found"));

        return userMapper.toResponseDto(user);
    }

    public UserResponseDto UpdateUser(UUID id, UserDto userDto) {
        Users user = userPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User with id " + id + " not found"));
        userMapper.updateDomainFromDto(userDto, user);

        Users updatedUser = userPersistencePort.save(user);

        return userMapper.toResponseDto(updatedUser);
    }

    public void deleteById(UUID id) {
        if (!userPersistencePort.existsById(id)) {
            throw new ResourceNotFound("User with id " + id + " not found");
        }
        userPersistencePort.deleteById(id);
    }
}
