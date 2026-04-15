package com.app.production.organization.infrastructure.persistence.adapters;

import com.app.production.organization.domain.entities.User;
import com.app.production.organization.domain.interfaces.UserPersistencePort;
import com.app.production.organization.infrastructure.persistence.entities.IUser;
import com.app.production.organization.infrastructure.persistence.repositories.UserRepository;
import com.app.production.organization.infrastructure.web.mappers.UserEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserPersistenceAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User save(User user) {
        IUser entity = userEntityMapper.toJpaEntity(user);
        IUser savedEntity = userRepository.save(entity);
        return userEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id)
                .map(userEntityMapper::toDomain);
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
