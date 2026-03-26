package com.app.production.organization.infrastructure.adapters;

import com.app.production.organization.domain.entities.User;
import com.app.production.organization.domain.interfaces.UserPersistencePort;
import com.app.production.organization.infrastructure.repositories.UserRepository;
import com.app.production.organization.application.mappers.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public User save(User user) {
        com.app.production.organization.infrastructure.persistence.entities.User entity = userMapper.toJpaEntity(user);
        com.app.production.organization.infrastructure.persistence.entities.User savedEntity = userRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) { return userRepository.existsById(id); }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteById(UUID id) { userRepository.deleteById(id); }
}
