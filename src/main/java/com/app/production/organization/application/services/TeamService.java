package com.app.production.organization.application.services;

import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices
public class TeamService {

    private final TeamService teamService;
    public TeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public void createTeam(){

    }
}
