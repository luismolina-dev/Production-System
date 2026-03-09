package com.app.production.auth.infrastructure.adapters;

import com.app.production.auth.domain.entities.Users;
import com.app.production.auth.domain.interfaces.UserPersistencePort;
import com.app.production.auth.infrastructure.persistence.entities.UserEntity;
import com.app.production.auth.infrastructure.repositories.UserRepository;
import com.app.production.auth.application.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Users save(Users user) {
        UserEntity entity = userMapper.toJpaEntity(user);
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<Users> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
