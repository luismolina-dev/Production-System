package com.app.production.organization.infrastructure.web.dtos.profile;

import com.app.production.organization.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {
    private UUID id;
    private String fullName;
    private String position;
    private Role role;
    private byte[] signature;
}
