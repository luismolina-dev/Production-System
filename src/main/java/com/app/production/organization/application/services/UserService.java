package com.app.production.organization.application.services;

import com.app.production.organization.application.dtos.UserDto;
import com.app.production.organization.application.dtos.UserResponseDto;
import com.app.production.organization.application.mappers.UserMapper;
import com.app.production.organization.domain.entities.Users;
import com.app.production.organization.domain.interfaces.UserPersistencePort;
import com.app.production.common.exceptions.ResourceNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserPersistencePort userPersistencePort;
    private final UserMapper userMapper;

    public UserService(UserPersistencePort userPersistencePort, UserMapper userMapper) {
        this.userPersistencePort = userPersistencePort;
        this.userMapper = userMapper;
    }

    public Page<UserResponseDto> getAll(Pageable pageable) {
        return userPersistencePort.findAll(pageable)
                .map(userMapper::toResponseDto);
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
