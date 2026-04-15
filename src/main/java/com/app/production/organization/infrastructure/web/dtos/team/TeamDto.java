package com.app.production.organization.infrastructure.web.dtos.team;

import com.app.production.organization.infrastructure.persistence.entities.IUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private String name;
    private IUser supervisorId;
}
