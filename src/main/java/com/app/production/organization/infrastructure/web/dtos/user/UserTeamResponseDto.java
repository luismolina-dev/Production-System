package com.app.production.organization.infrastructure.web.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTeamResponseDto {
    private UUID id;
    private String name;
}
