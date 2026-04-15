package com.app.production.organization.domain.interfaces;

import com.app.production.organization.domain.entities.Team;

import java.util.UUID;

public interface TeamPersistencePort {
    Team findById(UUID id);
    Team save(Team team);
    Team findAll();
    void delete(UUID id);
}
